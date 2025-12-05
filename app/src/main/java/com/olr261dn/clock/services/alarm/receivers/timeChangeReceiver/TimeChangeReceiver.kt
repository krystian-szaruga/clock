package com.olr261dn.clock.services.alarm.receivers.timeChangeReceiver

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseReceiver
import com.olr261dn.clock.services.alarm.workers.BootWorker
import com.olr261dn.clock.services.widget.utils.updateWidgetIntent

class TimeChangeReceiver: BaseReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        val action = intent?.action

        if (action == Intent.ACTION_TIME_CHANGED ||
            action == Intent.ACTION_TIMEZONE_CHANGED)
        {
            workerLauncher.launchWorker<BootWorker>(
                context = context,
                intent = intent,
                )
            updateWidgetIntent(context)
        }
    }
}

