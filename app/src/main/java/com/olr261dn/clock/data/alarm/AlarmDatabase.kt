package com.olr261dn.clock.data.alarm

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.model.converters.ArrayConverters
import com.olr261dn.clock.model.converters.CalendarTypeConverter
import com.olr261dn.clock.model.converters.DateTimeConverter

@Database(
    entities = [AlarmItem::class],
    version = 2,
    exportSchema = false)
@TypeConverters(DateTimeConverter::class, ArrayConverters::class, CalendarTypeConverter::class)
abstract class AlarmDatabase(): RoomDatabase() {
    abstract fun alarmDatabaseDao(): AlarmDatabaseDao
}

