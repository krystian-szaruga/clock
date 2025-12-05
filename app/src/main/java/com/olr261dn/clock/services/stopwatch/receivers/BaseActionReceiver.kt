package com.olr261dn.clock.services.stopwatch.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

abstract class BaseActionReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        context.sendBroadcast(getCustomIntent())
    }

    protected abstract fun getCustomIntent(): Intent
}