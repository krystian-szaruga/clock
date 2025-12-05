package com.olr261dn.clock.screens.alarm.components.handleAlarmState

import androidx.compose.runtime.Composable
import com.olr261dn.clock.components.actionState.Result
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.DisplayAlarms
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.error.DisplayErrorPopup
import com.olr261dn.clock.screens.alarm.components.handleAlarmState.loading.LoadingIndicator
import com.olr261dn.clock.viewModels.GlobalSettingsViewModel

@Composable
fun HandleAlarmState(
    globalSettingsViewModel: GlobalSettingsViewModel,
    alarms: Result<List<AlarmItem>>,
    onSnoozeCancel: (AlarmItem) -> Unit,
    onDeleteClick: (List<AlarmItem>) -> Unit,
    onItemClick: (AlarmItem) -> Unit,
    onToggleSwitch: (AlarmItem) -> Unit,
    onRetry: () -> Unit,
){
    when (alarms) {
        is Result.Loading -> LoadingIndicator()
        is Result.Success ->
            DisplayAlarms(
                globalSettingsViewModel = globalSettingsViewModel,
                alarms = alarms.data,
                onSnoozeCancel = { onSnoozeCancel(it) },
                onDeleteClick = { onDeleteClick(it) },
                onItemClick = { onItemClick(it) },
                onToggleSwitch = { onToggleSwitch(it) })
        is Result.Error -> DisplayErrorPopup(alarms.errorMessage){
            onRetry()
        }
    }
}
