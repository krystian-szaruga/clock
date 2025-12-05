package com.olr261dn.clock.services.alarm.receivers.baseReceiver

import android.content.BroadcastReceiver
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.ClearAlarmNotification
import com.olr261dn.clock.services.alarm.workers.utils.workerLauncher.WorkerLauncher

abstract class BaseReceiver: BroadcastReceiver() {
    val workerLauncher = WorkerLauncher()
    val clearAlarmNotification = ClearAlarmNotification()
}
