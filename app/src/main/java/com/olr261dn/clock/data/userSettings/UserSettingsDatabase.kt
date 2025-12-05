package com.olr261dn.clock.data.userSettings

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.olr261dn.clock.model.UserSettings
import com.olr261dn.clock.model.converters.TimeDurationConverter

@Database(
    entities = [UserSettings::class],
    version = 2,
    exportSchema = false)
@TypeConverters(TimeDurationConverter::class)
abstract class UserSettingsDatabase(): RoomDatabase(){
    abstract fun userSettingsDao(): UserSettingsDao
}
