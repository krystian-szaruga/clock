package com.olr261dn.clock.services.common.appNotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.olr261dn.clock.R
import com.olr261dn.clock.utils.timeConverters.localDateTime.TimeToString
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime

class MissedAlarmNotification(
    @ApplicationContext context: Context): AppNotification(context)
{
    private val inboxStyle = NotificationCompat.InboxStyle()
        .setBigContentTitle("Missed Alarms")

    fun showMissedNotifications(missedAlarms: List<LocalDateTime>) {
        createMissedAlarmNotificationChannel()
        for (missedAlarm in missedAlarms) {
            inboxStyle.addLine("Alarm at ${TimeToString(context, missedAlarm).convert()}")
        }
        displayNotification()
    }

    fun showAutoCancelNotification(missedAlarm: String) {
        createMissedAlarmNotificationChannel()
        inboxStyle.addLine(missedAlarm)
        displayNotification()
    }

    private fun displayNotification() {
        val notification = NotificationCompat.Builder(context, MISSED_ALARM_FIRING_ID)
            .setSmallIcon(R.drawable.baseline_access_alarm_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle("Missed Alarms")
            .setContentText("You have missed alarms.")
            .setStyle(inboxStyle)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(MISSED_ALARM_ID, notification)
    }

    private fun createMissedAlarmNotificationChannel(){
        NotificationChannel(
            MISSED_ALARM_FIRING_ID,
            "Missed Alarm",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            enableVibration(true)
            enableLights(true)
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            setSound(
                RingtoneManager.getDefaultUri(
                    RingtoneManager.TYPE_NOTIFICATION), audioAttributes)
            description = "Used for notifying missed alarms"
            notificationManager.createNotificationChannel(this)
        }
    }

    companion object {
        const val MISSED_ALARM_FIRING_ID = "missed alarm"
        const val MISSED_ALARM_ID = 600
    }
}
