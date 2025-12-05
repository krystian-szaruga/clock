package com.olr261dn.clock.services.alarm.receivers.earlyAlarmReceivers

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseReceiver
import com.olr261dn.clock.services.alarm.workers.EarlyAlarmWorker

class EarlyAlarmReceiver: BaseReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null){
            workerLauncher.launchWorker<EarlyAlarmWorker>(
                context = context.applicationContext,
                intent = intent
            )
        }
    }
}
