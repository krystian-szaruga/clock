package com.olr261dn.clock.services.alarm.alarmController.scheduler.pendingIntent

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.olr261dn.clock.MainActivity
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.services.alarm.receivers.alarmReceivers.AlarmReceiver
import com.olr261dn.clock.utils.timeConverters.localDateTime.TimeToString
import java.time.LocalDateTime

class AlarmPendingIntent(private val context: Context) {

    fun getPendingIntent(id: Int, time: LocalDateTime): PendingIntent {
        return PendingIntent.getBroadcast(
            context.applicationContext,
            id,
            getIntent(id, time),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getIntent(id: Int, time: LocalDateTime)
            : Intent {
        return Intent(context.applicationContext, AlarmReceiver::class.java).apply {
            putExtra("Id", id)
            putExtra("Time", TimeToString(context, time).convert())
        }
    }

    fun getInfoPendingItem(id: Int): PendingIntent? {
        val alarmInfoIntent = Intent(context.applicationContext, MainActivity::class.java).apply {
            putExtra("screen", ClockScreens.Alarm.name)
        }
        val alarmInfoPendingIntent = PendingIntent.getActivity(
            context, id, alarmInfoIntent, PendingIntent.FLAG_IMMUTABLE)
        return alarmInfoPendingIntent
    }


}