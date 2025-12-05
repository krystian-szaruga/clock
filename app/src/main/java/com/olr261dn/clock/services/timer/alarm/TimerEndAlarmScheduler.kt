package com.olr261dn.clock.services.timer.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.olr261dn.clock.MainActivity
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.services.timer.receiver.TimerEndReceiver

class TimerEndAlarmScheduler(private val context: Context) {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun setAlarm(time: Long, id: Int) {
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(time,
            getInfoPendingItem(id)),
            getPendingIntent(id)
        )
    }

    fun cancelAlarm(id: Int){
        alarmManager.cancel(getPendingIntent(id))
    }

    private fun getPendingIntent(id: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            id,
            Intent(context, TimerEndReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getInfoPendingItem(id: Int): PendingIntent? {
        return PendingIntent.getActivity(
            context,
            id,
            Intent(
                context.applicationContext,
                MainActivity::class.java).apply { putExtra("screen", ClockScreens.Timer.name) },
            PendingIntent.FLAG_IMMUTABLE)
    }
}