package com.olr261dn.clock.services.alarm.receivers.earlyAlarmReceivers

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseReceiver
import com.olr261dn.clock.services.alarm.workers.DismissUpcomingWorker

class DismissUpcomingReceiver: BaseReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getIntExtra("Id", 0) ?: 0
        clearAlarmNotification
            .dismissEarlyAlarmNotification(context, id)
        workerLauncher.launchWorker<DismissUpcomingWorker>(
            context = context,
            intent = intent)
    }
}
