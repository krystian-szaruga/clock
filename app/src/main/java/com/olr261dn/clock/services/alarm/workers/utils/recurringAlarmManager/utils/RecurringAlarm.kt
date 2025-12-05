package com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager.utils

import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.screens.alarm.utils.getTriggerTime
import java.time.LocalDateTime

class RecurringAlarm(
    private val alarmModifier: AlarmModifier,
    private val nonRecurringAlarm: NonRecurringAlarm,
    private val dismissUpcoming: Boolean)
{

    suspend fun handleRecurringAlarm(alarm: AlarmItem) {
        if (!alarm.isEnabled) {
            alarmModifier.cancelAlarm(alarm)
            return
        }
        var updatedAlarm = alarm.copy()

        if (updatedAlarm.isSingleOccurrence){
            val result = handleSingleOccurrence(updatedAlarm) ?: return
            updatedAlarm = result
        }
        updatedAlarm = updateAlarmTime(updatedAlarm)
        alarmModifier.saveAndSetAlarm(updatedAlarm)

    }

    private suspend fun handleSingleOccurrence(alarm: AlarmItem): AlarmItem?
    {
        val updatedAlarm = alarm.copy(
            repeatDays = alarm.repeatDays - LocalDateTime.now().dayOfWeek)

        return if (updatedAlarm.repeatDays.isEmpty()) {
            nonRecurringAlarm.handleNonRecurringAlarm(updatedAlarm)
            null
        } else {
            updatedAlarm
        }
    }

    private fun updateAlarmTime(alarm: AlarmItem): AlarmItem {
        return alarm.copy(
            time = getTriggerTime(
                alarm.time.hour,
                alarm.time.minute,
                alarm.repeatDays,
                dismissUpcoming
            )
        )
    }
}