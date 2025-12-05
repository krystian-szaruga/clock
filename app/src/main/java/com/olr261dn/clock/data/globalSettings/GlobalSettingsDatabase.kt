package com.olr261dn.clock.data.globalSettings

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olr261dn.clock.model.GlobalSettings

@Database(
    entities = [GlobalSettings::class],
    version = 3,
    exportSchema = false)
abstract class GlobalSettingsDatabase(): RoomDatabase(){
    abstract fun globalSettingsDao(): GlobalSettingsDao
}
