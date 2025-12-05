package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.olr261dn.clock.MainActivity
import com.olr261dn.clock.R
import com.olr261dn.clock.services.common.appNotification.AppNotification

abstract class TimeNotification(
    context: Context,
    private val service: String,
    private val requestCode: Int,
    private val notificationId: Int,
    private val clockScreen: String,
    private val name: String,
    private val overview: String): AppNotification(context)
{
    protected abstract fun buildNotification(
        times: List<String>, actualTime: String): Notification


    private fun createNotificationChannel(){
        val channel = NotificationChannel(
            service,
            name,
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            setSound(null, null)
            description = overview
        }
        notificationManager.createNotificationChannel(channel)
    }
    private fun getMainActivityIntent(): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("screen", clockScreen)
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        }
        return PendingIntent.getActivity(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    protected fun baseNotification(
        times: List<String>,
        title: String,
        text: String,
        inboxTitle: String): NotificationCompat.Builder
    {
        val maxSize = 5
        val inboxStyle = NotificationCompat.InboxStyle()
            .setBigContentTitle(inboxTitle)
        for (timer in times.take(maxSize)) {
            inboxStyle.addLine(timer)
        }
        if (times.size > maxSize) {
            inboxStyle.addLine("...")
        }

        return NotificationCompat.Builder(context, service)
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(inboxStyle)
            .setSmallIcon(R.drawable.ic_timer)
            .setContentIntent(getMainActivityIntent())
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }

    fun startNotification(times: List<String>, actualTime: String): Notification {
        createNotificationChannel()
        return buildNotification(times, actualTime)
    }

    fun updateNotification(times: List<String>, actualTime: String){
        val notification = buildNotification(times, actualTime)
        notificationManager.notify(notificationId, notification)
    }
}
