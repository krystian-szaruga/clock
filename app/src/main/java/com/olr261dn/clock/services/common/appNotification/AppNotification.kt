package com.olr261dn.clock.services.common.appNotification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers.AlarmNotification.Companion.ALARM_FIRING_ID
import com.olr261dn.clock.services.common.alarmSoundManager.AlarmSoundManager
import com.olr261dn.clock.utils.timeConverters.localDateTime.TimeToString
import java.time.LocalDateTime
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

abstract class AppNotification (protected val context: Context) {
    protected val notificationManager = context.getSystemService(
        Context.NOTIFICATION_SERVICE) as NotificationManager
    protected val timeoutDuration: Long = 300000

    fun dismissNotification(id: Int) {
        AlarmSoundManager.stopAlarmSound()
        notificationManager.cancel(id)
    }

    protected fun formatLocalDateTime(time: LocalDateTime): String =
        TimeToString(context, time).convert()
}