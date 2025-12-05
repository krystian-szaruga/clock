package com.olr261dn.clock.screens.alarm.utils

import java.time.Duration
import java.time.LocalDateTime

class AlarmOffset(alarmTime: LocalDateTime) {
    private val duration = Duration.between(LocalDateTime.now(), alarmTime)

    fun getAlarmOffset(): String {

        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60

        return when {
            days > 0 -> "Alarm is set to:\n" +
                    "$days days, $hours hours, $minutes minutes from now"
            hours > 0 -> "Alarm is set to:\n" +
                    "$hours hours, $minutes minutes from now"
            minutes > 0 -> "Alarm is set to:\n" +
                    "$minutes minutes from now"
            else -> "Alarm is set to:\n" +
                    "$seconds seconds from now"
        }
    }
}