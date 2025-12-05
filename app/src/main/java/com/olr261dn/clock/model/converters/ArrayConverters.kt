package com.olr261dn.clock.model.converters

import androidx.room.TypeConverter
import java.time.DayOfWeek

class ArrayConverters {

    @TypeConverter
    fun fromDayOfWeekSet(days: Set<DayOfWeek>?): String? {
        return days?.joinToString(separator = ",") { it.name }
    }

    @TypeConverter
    fun toDayOfWeekSet(daysString: String?): Set<DayOfWeek>? {
        return daysString?.split(",")
            ?.mapNotNull {
                try {
                    DayOfWeek.valueOf(it.trim().uppercase())
                } catch (e: IllegalArgumentException) {
                    null
                }
            }?.toSet()
    }

}