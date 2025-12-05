package com.olr261dn.clock.services.alarm.alarmController.scheduler.pendingIntent

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.earlyAlarmReceivers.EarlyAlarmReceiver

class EarlyAlarmPendingIntent(private val context: Context) {

    fun getPendingIntent(id: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            context.applicationContext,
            id,
            getIntent(id),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getIntent(id: Int): Intent {
        return Intent(context.applicationContext, EarlyAlarmReceiver::class.java).apply {
            putExtra("Id", id)
        }
    }
}