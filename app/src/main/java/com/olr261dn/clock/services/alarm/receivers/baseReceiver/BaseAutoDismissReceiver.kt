package com.olr261dn.clock.services.alarm.receivers.baseReceiver

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.serviceManager.ServiceManager
import com.olr261dn.clock.services.common.appNotification.MissedAlarmNotification

abstract class BaseAutoDismissReceiver: BaseReceiver() {
    protected var alarmFiringService: MissedAlarmNotification? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        alarmFiringService = ServiceManager {
            MissedAlarmNotification(it.applicationContext) }
            .getInstance(context.applicationContext)
    }
}
