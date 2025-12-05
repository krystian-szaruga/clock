package com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal

import android.content.Context
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.serviceManager.ServiceManager
import com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers.AlarmNotification
import com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers.EarlyAlarmNotification


class DismissNotification {

    fun dismiss(context: Context?, type: NotificationType, id: Int) {
        val serviceManager = when (type) {
            NotificationType.ALARM -> ServiceManager {
                AlarmNotification(it.applicationContext) }
                .getInstance(context?.applicationContext)
            NotificationType.EARLY_ALARM -> ServiceManager{
                EarlyAlarmNotification(it) }
                .getInstance(context?.applicationContext)
        }
        serviceManager?.dismissNotification(id)
    }
}

