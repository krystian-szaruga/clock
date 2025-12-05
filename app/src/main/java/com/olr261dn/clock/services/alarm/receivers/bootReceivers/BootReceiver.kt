package com.olr261dn.clock.services.alarm.receivers.bootReceivers

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseReceiver
import com.olr261dn.clock.services.alarm.workers.BootWorker


class BootReceiver: BaseReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            workerLauncher.launchWorker<BootWorker>(
                context = context,
                intent = intent)
        }
    }
}


