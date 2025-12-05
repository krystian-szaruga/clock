package com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.olr261dn.clock.R
import com.olr261dn.clock.services.alarm.receivers.earlyAlarmReceivers.DismissUpcomingReceiver
import com.olr261dn.clock.services.common.appNotification.AppNotification
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime

class EarlyAlarmNotification(
    @ApplicationContext context: Context) : AppNotification(context) {

    fun showNotification(id: Int, name: String, time: LocalDateTime) {
        createEarlyAlarmNotificationChannel()
        val earlyAlarmId = EARLY_ALARM_ID + id
        val timeFormatted = formatLocalDateTime(time)
        val notification = NotificationCompat.Builder(context, EARLY_ALARM_FIRING_ID)
            .setSmallIcon(R.drawable.baseline_access_alarm_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle("Early Alarm Notification")
            .setContentText("$name: $timeFormatted")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(
                R.drawable.baseline_done_24,
                "Dismiss Upcoming Alarm",
                PendingIntent.getBroadcast(
                    context.applicationContext,
                    id,
                    Intent(
                        context.applicationContext,
                        DismissUpcomingReceiver::class.java).apply {
                            putExtra("Id", id) },
                    PendingIntent.FLAG_IMMUTABLE
                )
            )

            .setAutoCancel(true)
            .build()
        notificationManager.notify(earlyAlarmId, notification)
    }
    private fun createEarlyAlarmNotificationChannel(){
        NotificationChannel(
            EARLY_ALARM_FIRING_ID,
            "Early Alarm Notification",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            enableVibration(false)
            enableLights(false)
            setSound(null, null)
            description = "Used for displaying early alarm notification"
            notificationManager.createNotificationChannel(this)
        }
    }


    companion object {
        const val EARLY_ALARM_FIRING_ID = "early alarm"
        const val EARLY_ALARM_ID = 5000
    }

}