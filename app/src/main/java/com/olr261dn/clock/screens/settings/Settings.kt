package com.olr261dn.clock.screens.settings

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.olr261dn.clock.components.scaffoldBar.ScaffoldBar
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.screens.alarm.viewModel.AlarmViewModel
import com.olr261dn.clock.screens.settings.settingsItem.AboutApp
import com.olr261dn.clock.screens.settings.settingsItem.SettingRow
import com.olr261dn.clock.screens.settings.settingsItem.SettingRowWithDropdown
import com.olr261dn.clock.screens.settings.utils.GetHorizontalDivider
import com.olr261dn.clock.services.alarm.alarmController.AlarmController
import com.olr261dn.clock.services.widget.utils.updateWidgetIntent
import com.olr261dn.clock.utils.checkFullScreenIntentsPermission.requestFullScreenIntentsPermission
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.timeFormatType.TimeFormatType
import com.olr261dn.clock.viewModels.GlobalSettingsViewModel


@Composable
fun Settings(
    navigateToScreen: NavigateToScreen,
    alarmViewModel: AlarmViewModel = hiltViewModel(),
    globalSettingsViewModel: GlobalSettingsViewModel = hiltViewModel()
){
    val snoozeTime = globalSettingsViewModel.snoozeTime.collectAsState()
    val timeFormat = globalSettingsViewModel.timeFormatType.collectAsState()
    val earlyAlarmReminder = globalSettingsViewModel.earlyAlarmReminder.collectAsState()
    val showAlarmInfo = globalSettingsViewModel.showAlarmInfo.collectAsState()
    val increaseVolumeGradually = globalSettingsViewModel.increaseVolumeGradually.collectAsState()
    val displaySecondsInWidget = globalSettingsViewModel.displaySecondsInWidget.collectAsState()
    val alarmsList = alarmViewModel.alarmList.collectAsState()
    val alarmController = AlarmController(LocalContext.current.applicationContext)
    val context = LocalContext.current

    ScaffoldBar(
        title = "Settings",
        navigateToScreen = navigateToScreen) {
        SurfaceWithColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    SettingRow(
                        label = "Early Alarm Reminder",
                        isChecked = earlyAlarmReminder.value
                    ) { earlyAlarmReminder ->
                        globalSettingsViewModel.updateEarlyAlarmReminder(earlyAlarmReminder)
                        updateEarlyAlarm(
                            alarmController = alarmController,
                            earlyAlarmReminder = earlyAlarmReminder,
                            alarmsList = alarmsList.value)
                    }
                }
                item {
                    SettingRow(
                        label = "Force Expand Alarm Info",
                        isChecked = showAlarmInfo.value
                    ) {
                        globalSettingsViewModel.updateShowAlarmInfo(it)
                    }
                }
                item {
                    SettingRow(
                        label = "Smooth Alarm Start",
                        isChecked = increaseVolumeGradually.value
                    ) {
                        globalSettingsViewModel.updateIncreaseVolumeGradually(it)
                    }
                }
                item {
                    SettingRow(
                        label = "Display Seconds in Widget ",
                        isChecked = displaySecondsInWidget.value
                    ) {
                        globalSettingsViewModel.updateDisplaySecondsInWidget(it)
                        updateWidgetIntent(context)
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    item {
                        FullScreenIntentPermission(context)
                    }
                }
                item{
                    SettingRowWithDropdown(
                        label = "Snooze Time",
                        selectedOption = snoozeTime.value,
                        options = listOf("5 min", "10 min", "15 min", "20 min")
                    ) {
                        globalSettingsViewModel.updateSnoozeTime(it)
                    }
                }
                item{
                    SettingRowWithDropdown(
                        label = "Time Format",
                        selectedOption = timeFormat.value,
                        options = listOf(
                            TimeFormatType.SYSTEM,
                            TimeFormatType.TWELVE_HOUR,
                            TimeFormatType.TWENTY_FOUR_HOUR
                        )
                    ) {
                        globalSettingsViewModel.updateTimeFormat(it, displaySecondsInWidget.value)
                        updateWidgetIntent(context = context)
                    }
                }
                item { AboutApp() }
            }
        }
    }
}

private fun updateEarlyAlarm(
    earlyAlarmReminder: Boolean,
    alarmController: AlarmController,
    alarmsList: List<AlarmItem>
) {
    if (earlyAlarmReminder) applyToAllAlarms(alarmsList) {
        alarmController.setEarlyAlarm(it) }
    else applyToAllAlarms(alarmsList) {
        alarmController.cancelEarlyAlarm(it) }
}

private fun applyToAllAlarms(alarmList: List<AlarmItem>, update: (AlarmItem) -> Unit){
    alarmList.filter { it.isEnabled }.forEach {
        update(it)
    }
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
private fun FullScreenIntentPermission(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(
            modifier = Modifier.clickable(
                onClick = { context.requestFullScreenIntentsPermission() }),
            text = "Full-Screen Notifications Settings",
            fontSize = 20.nonScaledSp)
    }
    GetHorizontalDivider()
}