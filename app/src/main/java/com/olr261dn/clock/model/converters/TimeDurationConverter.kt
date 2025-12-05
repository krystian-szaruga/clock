package com.olr261dn.clock.model.converters

import androidx.room.TypeConverter
import com.olr261dn.clock.screens.timer.components.utils.TimerDuration

class TimeDurationConverter {

    @TypeConverter
    fun durationToString(duration: TimerDuration): String {
        return "${duration.hour},${duration.minute},${duration.second},${duration.hourIndex},${duration.minuteIndex},${duration.secondIndex}"
    }

    @TypeConverter
    fun stringToDuration(durationString: String): TimerDuration {
        val parts = durationString.split(",")
        return TimerDuration(
            hour = parts.getOrNull(0)?.toInt() ?: 0,
            minute = parts.getOrNull(1)?.toInt() ?: 0,
            second = parts.getOrNull(2)?.toInt() ?: 0)
    }
}