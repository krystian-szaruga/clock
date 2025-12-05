package com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal

import android.content.Context
import com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers.AlarmNotification
import com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers.EarlyAlarmNotification

class ClearAlarmNotification {

    private val cancelNotification = DismissNotification()

    fun dismissEarlyAlarmNotification(context: Context?, id: Int){
        val earlyAlarmId = EarlyAlarmNotification.EARLY_ALARM_ID + id
        cancelNotification.dismiss(
            context?.applicationContext,
            NotificationType.EARLY_ALARM,
            earlyAlarmId)
    }

    fun dismissEarlyAndAlarmNotification(context: Context?, id: Int){
        cancelNotification.dismiss(
            context?.applicationContext,
            NotificationType.ALARM,
            AlarmNotification.ALARM_ID)
        dismissEarlyAlarmNotification(context?.applicationContext, id)

    }

}