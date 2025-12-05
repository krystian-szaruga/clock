package com.olr261dn.clock.services.alarm.alarmController.scheduler

import android.app.AlarmManager
import android.content.Context
import com.olr261dn.clock.services.alarm.alarmController.scheduler.pendingIntent.AlarmPendingIntent
import com.olr261dn.clock.services.alarm.alarmController.scheduler.pendingIntent.EarlyAlarmPendingIntent
import com.olr261dn.clock.utils.timeConverters.localDateTime.TimeToLong
import java.time.LocalDateTime
import kotlin.math.absoluteValue


class AlarmScheduler(context: Context){
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    private val alarmPendingIntent = AlarmPendingIntent(context.applicationContext)
    private val earlyAlarmPendingIntent = EarlyAlarmPendingIntent(context.applicationContext)

    fun setAlarm(id: Int, time: LocalDateTime){
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(
                TimeToLong(time).convert(),
                alarmPendingIntent.getInfoPendingItem(id)),
            alarmPendingIntent.getPendingIntent(id, time)
        )
    }

     fun setEarlyAlarm(id: Int, time: LocalDateTime) {
         var newTime = time.minusMinutes(20)
         newTime = if (newTime.isBefore(LocalDateTime.now()))
             LocalDateTime.now() else newTime
         alarmManager.setExactAndAllowWhileIdle(
             AlarmManager.RTC_WAKEUP,
             TimeToLong(newTime).convert(),
             earlyAlarmPendingIntent.getPendingIntent(id)
         )
     }

    fun cancelAlarm(id: Int, time: LocalDateTime, snoozeTime: LocalDateTime){
        alarmManager.cancel(alarmPendingIntent.getPendingIntent(id, time))
        alarmManager.cancel(alarmPendingIntent
            .getPendingIntent(id.absoluteValue * -1, snoozeTime))
    }

    fun cancelEarlyAlarm(id: Int){
        alarmManager.cancel(earlyAlarmPendingIntent.getPendingIntent(id))
        alarmManager.cancel(earlyAlarmPendingIntent.getPendingIntent(
            id.absoluteValue * -1))
    }
}
