package com.olr261dn.clock.services.timer.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.olr261dn.clock.R
import com.olr261dn.clock.services.common.alarmSoundManager.AlarmSoundManager
import com.olr261dn.clock.services.common.appNotification.AlarmSoundBehaviourProvider
import com.olr261dn.clock.services.common.appNotification.AppNotification
import com.olr261dn.clock.services.timer.fullScreenTimerFinishedActivity.TimerFinishedActivity
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.ALARM_TIMER_END_ID
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.REQUEST_CODE
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.TIMER_ALARM
import com.olr261dn.clock.services.timer.receiver.AutoDismissTimerReceiver
import com.olr261dn.clock.services.timer.receiver.DismissTimerReceiver
import dagger.hilt.android.qualifiers.ApplicationContext

class TimerEndNotification(@ApplicationContext context: Context): AppNotification(context) {
    private val alarmSoundBehaviourProvider = AlarmSoundBehaviourProvider(context)

    fun showNotification() {
        dismissNotification(ALARM_TIMER_END_ID)
        createChannel()
        AlarmSoundManager.playAlarmSound(
            context, alarmSoundBehaviourProvider.getIncreaseVolumeFlag())
        notificationManager.notify(ALARM_TIMER_END_ID, buildNotification().build())
    }

    private fun createChannel(){
        notificationManager.deleteNotificationChannel(TIMER_ALARM)
        NotificationChannel(
            TIMER_ALARM,
            "Timer",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            setSound(null, null)
            description = "Used to trigger a notification when the timer ends"
            notificationManager.createNotificationChannel(this)
        }
    }

    private fun buildNotification(): NotificationCompat.Builder {
        return NotificationCompat.Builder(
            context, TIMER_ALARM)
            .setContentTitle("Timer")
            .setTimeoutAfter(timeoutDuration)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDeleteIntent(getAutoCancelPendingIntent())
            .setContentIntent(getFullScreenPendingIntent())
            .setFullScreenIntent(
                getFullScreenPendingIntent(), true)
            .addAction(
                R.drawable.baseline_cancel_24,
                "Dismiss",
                getDismissPendingIntent())
            .setContentText("00:00:00")
            .setAutoCancel(false)
            .setSmallIcon(R.drawable.ic_timer)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    private fun getFullScreenPendingIntent(): PendingIntent {
        return PendingIntent.getActivity(
            context.applicationContext,
            REQUEST_CODE,
            Intent(context, TimerFinishedActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getAutoCancelPendingIntent(): PendingIntent{
        return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            Intent(
                context.applicationContext,
                AutoDismissTimerReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }
    private fun getDismissPendingIntent(): PendingIntent =
        PendingIntent.getBroadcast(
            context.applicationContext,
            REQUEST_CODE,
            Intent(
                context.applicationContext,
                DismissTimerReceiver::class.java
            ),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
}
