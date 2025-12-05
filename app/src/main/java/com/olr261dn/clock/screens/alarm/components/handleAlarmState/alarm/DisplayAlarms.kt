package com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor
import com.olr261dn.clock.components.commonComposable.EmptyScreenMsg
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils.AdditionalAlarmData
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils.DisplayLabel
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils.DisplaySnoozeAlarmInfo
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils.LongClickOption
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils.ToggleSwitchWithIndicator
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import com.olr261dn.clock.utils.timeConverters.localDateTime.TimeToStringWithPattern
import com.olr261dn.clock.viewModels.GlobalSettingsViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayAlarms(
    alarms: List<AlarmItem>,
    globalSettingsViewModel: GlobalSettingsViewModel,
    onSnoozeCancel: (AlarmItem) -> Unit,
    onDeleteClick: (List<AlarmItem>) -> Unit,
    onItemClick: (AlarmItem) -> Unit,
    onToggleSwitch: (AlarmItem) -> Unit)
{
    val showAlarmInfo = globalSettingsViewModel.showAlarmInfo.collectAsState()
    val timeFormat = globalSettingsViewModel.timeFormat.collectAsState()
    var displayLongClickOptions by remember {
        mutableStateOf(false) }
    var selectedItems by remember {
        mutableStateOf(emptyList<AlarmItem>()) }
    globalSettingsViewModel.initialize()

    if (alarms.isEmpty()) EmptyScreenMsg("Press the '+' button to add an alarm")
    Column {
        if (displayLongClickOptions)
            LongClickOption(
                totalElem = alarms.size,
                selectedElem = selectedItems.size,
                onSelectAll = {
                    selectedItems = when {
                        selectedItems.size == alarms.size -> emptyList()
                        else -> alarms }
                },
                onCancelClick = {
                    displayLongClickOptions = false
                    selectedItems = emptyList()
                }
            ) {
                displayLongClickOptions = false
                onDeleteClick(selectedItems)
                selectedItems = emptyList()
            }
        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.9f)
        ) {
            items(alarms.filter { !it.isSnooze }) { alarm ->
                AlarmItemRow(
                    timeFormat = timeFormat.value,
                    alarms = alarms,
                    alarm = alarm,
                    isSelected = selectedItems.contains(alarm),
                    showAlarmInfo = showAlarmInfo.value,
                    onClick = {
                        if (displayLongClickOptions)
                            selectedItems = if (alarm in selectedItems)
                                selectedItems - alarm
                            else selectedItems + alarm
                        else onItemClick(alarm)
                    },
                    onLongClick = {
                        selectedItems = selectedItems + alarm
                        displayLongClickOptions = true
                    },
                    onSnoozeCancel = { onSnoozeCancel(it) },
                    onToggleSwitch = { onToggleSwitch(alarm.copy(isEnabled = it)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AlarmItemRow(
    timeFormat: String = "hh:mm:ss",
    alarms: List<AlarmItem>,
    alarm: AlarmItem,
    isSelected: Boolean,
    showAlarmInfo: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onSnoozeCancel: (AlarmItem) -> Unit,
    onToggleSwitch: (Boolean) -> Unit
){
    val isSnooze = alarms.firstOrNull { it.id == (alarm.id * -1) }
    var expanded by rememberSaveable { mutableStateOf(alarm.isEnabled) }
    val backgroundColor = if (isSelected) getSecondBackgroundColor() else
        getBackgroundColor()


    SurfaceWithColumn(
        padding = 10.dp,
        modifier = Modifier.combinedClickable(
            onClick = { onClick()
            },
            onLongClick = { onLongClick() })
            .padding(2.dp),
        customBackgroundColor = backgroundColor,
        dropDownAction = if (showAlarmInfo){ null } else {
            { expanded = !expanded } },
        dropDownIcon = if (!expanded) Icons.Default.KeyboardArrowDown else
            Icons.Default.KeyboardArrowUp) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            DisplayLabel(
                size = if (isSnooze != null) 35.nonScaledSp else
                    50.nonScaledSp,
                label = TimeToStringWithPattern(alarm.time, timeFormat).convert(),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
            isSnooze?.let { snoozeAlarm ->
                DisplaySnoozeAlarmInfo(timeFormat, snoozeAlarm) { onSnoozeCancel(it) }
            }
            ToggleSwitchWithIndicator(alarm.isEnabled) {
                onToggleSwitch(it)
                expanded = !alarm.isEnabled
            }
        }
        if (expanded || showAlarmInfo) AdditionalAlarmData(alarm)
    }
}