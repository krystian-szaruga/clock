package com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager.utils

import android.content.Context
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.services.widget.utils.updateWidgetIntent
import java.time.LocalDateTime
import java.time.ZoneId

class NonRecurringAlarm(
    private val alarmModifier: AlarmModifier,
    private val context: Context,
    private val startFromBoot: Boolean)
{
    suspend fun handleNonRecurringAlarm(alarm: AlarmItem)
    {
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        if (alarm.time.isAfter(currentTime) && startFromBoot) {
            val updatedAlarm = alarm.copy(isEnabled = true)
            alarmModifier.saveAndSetAlarm(updatedAlarm)
            return
        }
        handlePastNonRecurringAlarm(alarm)
        updateWidgetIntent(context.applicationContext)
    }

    private suspend fun handlePastNonRecurringAlarm(alarm: AlarmItem){
        if (alarm.isSnooze) {
            alarmModifier.deleteAlarm(alarm.id)
            alarmModifier.cancelAlarm(alarm)
        } else {
            val updatedAlarm = alarm.copy(isEnabled = false, isSingleOccurrence = false)
            alarmModifier.updateAlarm(updatedAlarm)
            alarmModifier.cancelAlarm(updatedAlarm)
        }
    }
}