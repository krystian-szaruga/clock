package com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager

import android.content.Context
import android.util.Log
import com.olr261dn.clock.services.alarm.alarmController.AlarmController
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager.utils.AlarmModifier
import com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager.utils.NonRecurringAlarm
import com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager.utils.RecurringAlarm

class SetNextAlarmWrapper(
    repository: AlarmRepository,
    context: Context,
    startFromBoot: Boolean = false,
    dismissUpcoming: Boolean = false)
{
    private val alarmController = AlarmController(
        context.applicationContext)
    private val alarmModifier = AlarmModifier(
        repository, alarmController)
    private val nonRecurringAlarm = NonRecurringAlarm(
        alarmModifier, context, startFromBoot)
    private val recurringAlarm = RecurringAlarm(
        alarmModifier, nonRecurringAlarm, dismissUpcoming)

    fun addEarlyAlarmReminderFlag(earlyAlarmReminder: Boolean){
        alarmModifier.updateEarlyAlarmReminderFlag(earlyAlarmReminder)
    }

    suspend fun setNextAlarm(alarm: AlarmItem) {
        try {
            if (alarm.isRecurring) {
                recurringAlarm.handleRecurringAlarm(alarm)
            } else {
                nonRecurringAlarm.handleNonRecurringAlarm(alarm)
            }
        } catch (e: Exception) {
            Log.e(
                "RecurringAlarmManager",
                "Error setting next alarm for ID: ${alarm.id}", e)
        }
    }
}
