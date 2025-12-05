package com.olr261dn.clock.screens.alarm.utils

import java.time.DayOfWeek
import java.time.LocalDateTime

fun getTriggerTime(
    hour: Int,
    minute: Int,
    alarmDays: Set<DayOfWeek>,
    dismissUpcoming: Boolean = false): LocalDateTime {
    val currentTime = LocalDateTime.now()
    val todayDayOfWeek = currentTime.dayOfWeek
    val triggerTimeToday = LocalDateTime.now()
        .withHour(hour)
        .withMinute(minute)
        .withSecond(0)
        .withNano(0)

    if (alarmDays.isEmpty()) {
        return if (triggerTimeToday.isBefore(currentTime))
            triggerTimeToday.plusDays(1) else triggerTimeToday
    }
    if (
        alarmDays.contains(todayDayOfWeek) &&
        triggerTimeToday.isAfter(currentTime) &&
        !dismissUpcoming) {
        return triggerTimeToday
    }

    for (day in alarmDays) {
        if (day.value > todayDayOfWeek.value) {
            val daysUntilNext = day.value - todayDayOfWeek.value
            return triggerTimeToday.plusDays(daysUntilNext.toLong())
        }
    }
    val firstDay = alarmDays.first()
    val daysUntilFirstNextWeek = 7 - todayDayOfWeek.value + firstDay.value
    return triggerTimeToday.plusDays(daysUntilFirstNextWeek.toLong())
}