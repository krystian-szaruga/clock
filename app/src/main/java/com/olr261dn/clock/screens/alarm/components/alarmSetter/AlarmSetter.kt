package com.olr261dn.clock.screens.alarm.components.alarmSetter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.screens.alarm.components.alarmSetter.components.dayPicker.DayPicker
import com.olr261dn.clock.screens.alarm.components.alarmSetter.components.labelField.LabelField
import com.olr261dn.clock.screens.alarm.components.alarmSetter.components.relativeTimeLabel.RelativeTimeLabel
import com.olr261dn.clock.screens.alarm.components.alarmSetter.components.saveAlarms.SaveAlarm
import com.olr261dn.clock.screens.alarm.components.alarmSetter.components.singleOccurrenceMode.SingleOccurrenceMode
import com.olr261dn.clock.screens.alarm.components.alarmSetter.components.timePicker.ShowTimePicker
import com.olr261dn.clock.screens.alarm.components.alarmSetter.components.title.Title
import com.olr261dn.clock.screens.alarm.utils.getTriggerTime
import com.olr261dn.clock.viewModels.GlobalSettingsViewModel
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmSetter(
    globalSettingsViewModel: GlobalSettingsViewModel,
    alarmItem: AlarmItem?,
    getUniqueId: () -> Int,
    onSave: (AlarmItem) -> Unit) {

    val alarmLabel = rememberSaveable { mutableStateOf(alarmItem?.label ?: "Alarm") }
    val timeFormat = globalSettingsViewModel.timeFormat.collectAsState()
    val time = alarmItem?.time ?: LocalDateTime.now().plusHours(8)
    val is24HourFormat = !timeFormat.value.contains("a")
    val selectedDays = remember { mutableStateOf(alarmItem?.repeatDays ?: setOf()) }
    val timePickerState = rememberTimePickerState(
        initialMinute = time.minute,
        initialHour = time.hour,
        is24Hour = is24HourFormat
    )
    val triggerTime = remember {
        derivedStateOf {
            getTriggerTime(
                timePickerState.hour,
                timePickerState.minute,
                selectedDays.value.sorted().toSet()
            )
        }
    }
    val label = remember(triggerTime.value) {
        val currentTime = LocalDateTime.now()
        val daysDifference = ChronoUnit.DAYS.between(
            currentTime.toLocalDate(), triggerTime.value.toLocalDate())

        when {
            daysDifference == 0L -> "Today"
            daysDifference == 1L -> "Tomorrow"
            daysDifference > 1L -> "$daysDifference days from now"
            else -> "In the past"
        }
    }
    val isSingleOccurrence = remember { mutableStateOf(alarmItem?.isSingleOccurrence == true)
    }

    SurfaceWithColumn(
        customBackgroundColor = getSecondBackgroundColor()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.95f)
                .padding(start = 4.dp, end = 4.dp, top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Title()
                ShowTimePicker(timePickerState)
                RelativeTimeLabel(label=label)
                LabelField(alarmLabel)
                DayPicker(selectedDays = selectedDays)
                SingleOccurrenceMode(
                    isSingleOccurrence.value,
                    selectedDays.value.isNotEmpty()){
                    isSingleOccurrence.value = !isSingleOccurrence.value
                }
                SaveAlarm {
                    val sortedDays = selectedDays.value.sorted().toSet()
                    val newAlarm = AlarmItem(
                        id = alarmItem?.id ?: getUniqueId(),
                        isRecurring = sortedDays.isNotEmpty(),
                        label = alarmLabel.value,
                        repeatDays = sortedDays,
                        isSingleOccurrence = isSingleOccurrence.value,
                        time = getTriggerTime(
                            timePickerState.hour,
                            timePickerState.minute,
                            sortedDays))
                    onSave(newAlarm)
                }
            }
        }
    }
}

