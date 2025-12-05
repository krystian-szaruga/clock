package com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.olr261dn.clock.R
import com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.FullScreenAlarmActivity
import com.olr261dn.clock.services.alarm.receivers.alarmReceivers.DismissAlarmReceiver
import com.olr261dn.clock.services.alarm.receivers.alarmReceivers.SnoozeDismissReceiver
import com.olr261dn.clock.services.alarm.receivers.autoCanceledAlarmReceiver.AutoCanceledAlarmReceiver
import com.olr261dn.clock.services.common.alarmSoundManager.AlarmSoundManager
import com.olr261dn.clock.services.common.appNotification.AlarmSoundBehaviourProvider
import com.olr261dn.clock.services.common.appNotification.AppNotification
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime


class AlarmNotification (
    @ApplicationContext context: Context): AppNotification(context)
{
    private var name: String? = null
    private val alarmSoundBehaviourProvider = AlarmSoundBehaviourProvider(context)

    fun showNotification(id: Int, name: String?, time: LocalDateTime) {
        createAlarmFiringNotificationChannel()
        this.name = name
        val timeFormatted = formatLocalDateTime(time)

        val notification = NotificationCompat.Builder(context, ALARM_FIRING_ID)
            .setSmallIcon(R.drawable.baseline_access_alarm_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(name)
            .setTimeoutAfter(timeoutDuration)
            .setDeleteIntent(
                getAutoCancelPendingIntent(id, timeFormatted))
            .setContentIntent(getFullScreenPendingIntent(id, timeFormatted))
            .setContentText(timeFormatted)
            .setFullScreenIntent(
                getFullScreenPendingIntent(id, timeFormatted), true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(
                R.drawable.baseline_done_24,
                "Dismiss",
                getDismissPendingIntent(id))
            .addAction(
                R.drawable.ic_hourglass,
                "Snooze",
                getSnoozePendingIntent(id)
            )
            .setAutoCancel(false)
            .build()
        notificationManager.notify(ALARM_ID, notification)
        AlarmSoundManager.playAlarmSound(
            context, alarmSoundBehaviourProvider.getIncreaseVolumeFlag())
    }

    private fun getFullScreenPendingIntent(
        id: Int,
        timeFormatted: String): PendingIntent {
        val activityIntent = Intent(context, FullScreenAlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("Id", id)
            putExtra("Name", name)
            putExtra("Time", timeFormatted)
        }
        return getActivity(activityIntent, id)
    }

    private fun getSnoozePendingIntent(id: Int): PendingIntent {
        val snoozeIntent = Intent(context, SnoozeDismissReceiver::class.java)
            .putExtra("Id", id)
        return getBroadcast(snoozeIntent, id)
    }

    private fun getDismissPendingIntent(id: Int): PendingIntent {
        val dismissIntent = Intent(context, DismissAlarmReceiver::class.java)
            .putExtra("Id", id)
        return getBroadcast(dismissIntent, id)
    }

    private fun getAutoCancelPendingIntent(
        id: Int,
        timeFormatted: String): PendingIntent{
        val missedIntent = Intent(
            context.applicationContext,
            AutoCanceledAlarmReceiver::class.java)
            .putExtra("Id", id)
            .putExtra("time", timeFormatted)
        return getBroadcast(missedIntent, id)
    }

    private fun getBroadcast(customIntent: Intent, id: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            context.applicationContext,
            id,
            customIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun getActivity(customIntent: Intent, id: Int): PendingIntent {
        return PendingIntent.getActivity(
            context.applicationContext,
            id,
            customIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )
    }
    private fun createAlarmFiringNotificationChannel() {
        notificationManager.deleteNotificationChannel(ALARM_FIRING_ID)
        NotificationChannel(
            ALARM_FIRING_ID,
            "Alarm",
            NotificationManager.IMPORTANCE_HIGH)
            .apply {
                setSound(null, null)
                description = "Used For Firing An Alarm"
                notificationManager.createNotificationChannel(this)
            }
    }

    companion object{
        const val ALARM_FIRING_ID = "Alarm"
        const val ALARM_ID = 551
    }
}
