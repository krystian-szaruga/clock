package com.olr261dn.clock.model.converters

import androidx.room.TypeConverter
import java.util.Calendar

class CalendarTypeConverter {

    @TypeConverter
    fun fromCalendar(calendar: Calendar?): Long? {
        return calendar?.timeInMillis
    }

    @TypeConverter
    fun toCalendar(millis: Long?): Calendar? {
        return millis?.let {
            Calendar.getInstance().apply { timeInMillis = it }
        }
    }
}