package com.olr261dn.clock.data.timeZone

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olr261dn.clock.model.Zone

@Dao
interface ZoneDatabaseDao {
    @Query("SELECT * FROM zone_ids")
    suspend fun getZones(): List<Zone>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZone(zone: Zone)

    @Delete
    suspend fun deleteZone(zone: Zone)

    @Query("DELETE FROM zone_ids")
    suspend fun deleteAll()

}