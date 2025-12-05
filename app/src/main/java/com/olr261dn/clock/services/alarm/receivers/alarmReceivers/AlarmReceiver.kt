package com.olr261dn.clock.services.alarm.receivers.alarmReceivers

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseReceiver
import com.olr261dn.clock.services.alarm.workers.AlarmWorker


class AlarmReceiver : BaseReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        workerLauncher.launchWorker<AlarmWorker>(
            context = context,
            intent = intent)
    }
}
