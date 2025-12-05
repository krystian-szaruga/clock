package com.olr261dn.clock.services.stopwatch.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.olr261dn.clock.R
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification.TimeNotification
import com.olr261dn.clock.services.stopwatch.receivers.ContinueReceiver
import com.olr261dn.clock.services.stopwatch.receivers.LapReceiver
import com.olr261dn.clock.services.stopwatch.receivers.StopReceiver


class StopwatchStateNotification(context: Context):
    TimeNotification(
        context, STOPWATCH_SERVICE, REQUEST_CODE, NOTIFICATION_ID,
        ClockScreens.Stopwatch.name, NAME, DESCRIPTION) {

    override fun buildNotification(
        times: List<String>,
        actualTime: String): Notification {

        return baseNotification(
            times = times,
            title = "Stopwatch",
            text = actualTime,
            inboxTitle = "Stopwatch:\n$actualTime")
            .addAction(R.drawable.ic_timer, "Lap", getPendingIntent(
                Intent(context, LapReceiver::class.java)))
            .addAction(R.drawable.ic_timer, "Stop", getPendingIntent(
                Intent(context, StopReceiver::class.java)))
            .addAction(R.drawable.ic_timer, "Continue", getPendingIntent(
                Intent(context, ContinueReceiver::class.java)))
            .build()
    }

    private fun getPendingIntent(intent: Intent): PendingIntent? {
        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        const val STOPWATCH_SERVICE = "stopwatch_service"
        const val NOTIFICATION_ID = 888
        const val REQUEST_CODE = 889
        const val NAME = "Current Stopwatch State"
        const val DESCRIPTION = "Used For Displaying Stopwatch State"

    }
}