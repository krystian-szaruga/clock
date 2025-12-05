package com.olr261dn.clock.data.timeZone

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olr261dn.clock.model.Zone

@Database(
    entities = [Zone::class],
    version = 1,
    exportSchema = false)
abstract class ZoneDatabase: RoomDatabase() {
    abstract fun zoneDao(): ZoneDatabaseDao
}