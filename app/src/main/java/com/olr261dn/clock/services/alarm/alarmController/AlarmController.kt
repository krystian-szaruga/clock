package com.olr261dn.clock.services.alarm.alarmController

import android.content.Context
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.services.alarm.alarmController.scheduler.AlarmScheduler
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.ClearAlarmNotification
import com.olr261dn.clock.services.widget.utils.updateWidgetIntent

class AlarmController(private val context: Context)
{
    private val alarmScheduler = AlarmScheduler(context.applicationContext)
    private val clearAlarmNotification = ClearAlarmNotification()

    fun setAlarm(alarmItem: AlarmItem, earlyAlarmReminder: Boolean = true) {
        if (earlyAlarmReminder) setEarlyAlarm(alarmItem)
        alarmScheduler.setAlarm(
            id = alarmItem.id,
            time = alarmItem.time)
        updateWidgetIntent(context)
    }

    fun setEarlyAlarm(alarmItem: AlarmItem){
        alarmScheduler.setEarlyAlarm(alarmItem.id, alarmItem.time)
    }

    fun cancelAlarm(alarmItem: AlarmItem){
        cancelEarlyAlarm(alarmItem)
        alarmScheduler.cancelAlarm(
            id = alarmItem.id,
            time = alarmItem.time,
            snoozeTime = alarmItem.snoozeTime
        )
        clearAlarmNotification.dismissEarlyAlarmNotification(
            context, alarmItem.id * -1)
        updateWidgetIntent(context)
    }

    fun cancelEarlyAlarm(alarmItem: AlarmItem){
        alarmScheduler.cancelEarlyAlarm(alarmItem.id)
        clearAlarmNotification.dismissEarlyAlarmNotification(
            context, alarmItem.id)
    }
}

