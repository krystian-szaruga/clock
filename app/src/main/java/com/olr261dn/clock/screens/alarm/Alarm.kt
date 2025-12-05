package com.olr261dn.clock.screens.alarm

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.olr261dn.clock.components.actionState.Result
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getOnPrimaryContainerColor
import com.olr261dn.clock.components.colors.common.getPrimaryContainerColor
import com.olr261dn.clock.components.scaffoldBar.ScaffoldBar
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.screens.alarm.components.alarmSetter.AlarmSetter
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.HandleAlarmState
import com.olr261dn.clock.screens.alarm.utils.SnackBar
import com.olr261dn.clock.screens.alarm.utils.getTriggerTime
import com.olr261dn.clock.screens.alarm.viewModel.AlarmViewModel
import com.olr261dn.clock.services.alarm.alarmController.AlarmController
import com.olr261dn.clock.viewModels.GlobalSettingsViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime


@Composable
fun Alarm(
    navigateToScreen: NavigateToScreen,
    globalSettingsViewModel: GlobalSettingsViewModel = hiltViewModel(),
    alarmViewModel: AlarmViewModel = hiltViewModel()
) {
    val earlyAlarmReminder = globalSettingsViewModel.earlyAlarmReminder.collectAsState()
    val alarms = alarmViewModel.alarms.collectAsState()
    val context = LocalContext.current.applicationContext
    val alarmController = AlarmController(context)
    val snackBar = SnackBar(rememberCoroutineScope(), remember { SnackbarHostState() })
    val showAddAlarm = rememberSaveable {
        mutableStateOf(false) }
    var alarmItem by remember {
        mutableStateOf<AlarmItem?>(null) }

    ScaffoldBar(
        title = "Alarm",
        navigateToScreen = navigateToScreen,
        initialScrollIndex = 2,
        fabIcon = if (showAddAlarm.value) Icons.Default.Close else
            Icons.Default.Add,
        fabContentOnTap = {
            alarmItem = null
            showAddAlarm.value = !showAddAlarm.value })
    { paddingValue ->
        Surface(
            modifier = Modifier.padding(paddingValue),
            color = getBackgroundColor())
        {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showAddAlarm.value) {
                    AlarmSetterContent(
                        globalSettingsViewModel = globalSettingsViewModel,
                        alarmItem = alarmItem,
                        alarmViewModel = alarmViewModel,
                        alarmController = alarmController,
                        earlyAlarmReminder = earlyAlarmReminder.value
                    )
                    {
                        showAddAlarm.value = false
                        snackBar.showSnackBar(it)
                    }
                }
                AlarmScreenContent(
                    globalSettingsViewModel = globalSettingsViewModel,
                    alarms = alarms,
                    navigateToScreen = navigateToScreen,
                    alarmViewModel = alarmViewModel,
                    alarmController = alarmController,
                    earlyAlarmReminder = earlyAlarmReminder.value,
                    onToggleSwitch = {
                        snackBar.showSnackBar(it)
                    }) {
                    alarmItem = it
                    showAddAlarm.value = !showAddAlarm.value
                }
            }
            SnackbarHost(
                hostState = snackBar.snackBarHostState,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.BottomStart))
            {
                Snackbar(
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures {
                            snackBar.coroutineScope.launch {
                                snackBar
                                    .snackBarHostState
                                    .currentSnackbarData?.dismiss()
                            }
                        }
                    },
                    snackbarData = it,
                    containerColor = getPrimaryContainerColor(),
                    contentColor = getOnPrimaryContainerColor()
                )
            }
        }
    }
}


@Composable
private fun AlarmSetterContent(
    globalSettingsViewModel: GlobalSettingsViewModel,
    alarmItem: AlarmItem?,
    alarmViewModel: AlarmViewModel,
    alarmController: AlarmController,
    earlyAlarmReminder: Boolean,
    onSave: (LocalDateTime) -> Unit,
){
    AlarmSetter(
        globalSettingsViewModel = globalSettingsViewModel,
        alarmItem = alarmItem,
        getUniqueId = { alarmViewModel.getUniqueId() })
    {
        alarmViewModel.deleteAlarmItem(it.id * -1)
        alarmViewModel.addAlarmItem(it)
        onSave(it.time)
        alarmController.cancelAlarm(it)
        alarmController.setAlarm(it, earlyAlarmReminder)
    }
}

@Composable
private fun AlarmScreenContent(
    globalSettingsViewModel: GlobalSettingsViewModel,
    alarms: State<Result<List<AlarmItem>>>,
    navigateToScreen: NavigateToScreen,
    alarmViewModel: AlarmViewModel,
    alarmController: AlarmController,
    earlyAlarmReminder: Boolean,
    onToggleSwitch: (LocalDateTime) -> Unit,
    onItemClick: (AlarmItem?) -> Unit){

    HandleAlarmState(
        globalSettingsViewModel = globalSettingsViewModel,
        alarms = alarms.value,
        onSnoozeCancel = { alarm ->
            alarmViewModel.deleteAlarmItem(alarm.id)
            alarmController.cancelAlarm(alarm)
        },
        onDeleteClick = { alarmList ->
            alarmList.forEach { alarmController.cancelAlarm(it) }
            alarmViewModel.deleteAlarmItems(alarmList) },
        onItemClick = { alarm ->
            onItemClick(alarm)},
        onToggleSwitch = { alarm ->
            if (!alarm.isEnabled) {
                alarmController.cancelAlarm(alarm)
                alarmViewModel.deleteAlarmItem(id = alarm.id * -1)
                alarmViewModel.updateAlarmItem(alarm)
            } else {
                val updatedAlarm = alarm.copy(
                    time = getTriggerTime(
                        alarm.time.hour,
                        alarm.time.minute, alarm.repeatDays))
                alarmViewModel.updateAlarmItem(updatedAlarm)
                alarmController.setAlarm(updatedAlarm, earlyAlarmReminder)
                onToggleSwitch(updatedAlarm.time)
            }

        },
        onRetry = { navigateToScreen.navigateAndClearBackStack(ClockScreens.Clock.name) }
    )
}
