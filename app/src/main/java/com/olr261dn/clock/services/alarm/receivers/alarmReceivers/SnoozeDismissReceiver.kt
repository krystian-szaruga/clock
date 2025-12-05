package com.olr261dn.clock.services.alarm.receivers.alarmReceivers

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseReceiver
import com.olr261dn.clock.services.alarm.workers.SnoozeWorker

class SnoozeDismissReceiver: BaseReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getIntExtra("Id", 0) ?: 0
        clearAlarmNotification.dismissEarlyAndAlarmNotification(
            context, id)
        workerLauncher.launchWorker<SnoozeWorker>(
            context = context,
            intent = intent)
    }
}
