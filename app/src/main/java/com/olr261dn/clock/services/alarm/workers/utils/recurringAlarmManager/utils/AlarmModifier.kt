package com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager.utils

import android.util.Log
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.services.alarm.alarmController.AlarmController

class AlarmModifier(
    private val alarmRepository: AlarmRepository,
    private val alarmController: AlarmController)
{
    private var earlyAlarmReminder: Boolean = true

    fun updateEarlyAlarmReminderFlag(flag: Boolean) {
        earlyAlarmReminder = flag
    }

    suspend fun saveAndSetAlarm(alarm: AlarmItem) {
        updateAlarm(alarm)
        setAlarm(alarm)
    }

    suspend fun updateAlarm(alarm: AlarmItem) {
        alarmRepository.updateAlarm(alarm)
        Log.d(
            "AlarmModifier",
            "Alarm updated for ID: ${alarm.id}")
    }

    private fun setAlarm(alarm: AlarmItem){
        alarmController.setAlarm(alarm, earlyAlarmReminder)
        Log.d(
            "AlarmModifier",
            "Alarm set for ID: ${alarm.id}")
    }

    suspend fun deleteAlarm(id: Int){
        alarmRepository.deleteAlarm(id)
        Log.d(
            "RecurringAlarmManager",
            "Alarm deleted for ID: $id")
    }

    fun cancelAlarm(alarm: AlarmItem) {
        alarmController.cancelAlarm(alarm)
    }
}
