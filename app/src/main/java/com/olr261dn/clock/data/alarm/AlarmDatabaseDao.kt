package com.olr261dn.clock.data.alarm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.olr261dn.clock.model.AlarmItem

@Dao
interface AlarmDatabaseDao {

    @Query("SELECT * FROM alarm")
    suspend fun getAlarms(): List<AlarmItem>

    @Query("SELECT * FROM alarm WHERE id = :id")
    suspend fun getAlarm(id: Int): AlarmItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarmItem: AlarmItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAlarm(alarmItem: AlarmItem)

    @Query("DELETE FROM alarm WHERE id = :id")
    suspend fun deleteAlarm(id: Int)

    @Query("DELETE FROM alarm WHERE id IN (:ids)")
    suspend fun deleteAlarms(ids: List<Int>)

    @Query("UPDATE alarm SET is_enabled = :isEnabled WHERE id = :id")
    suspend fun updateEnabledState(isEnabled: Boolean, id: Int)

    @Query("SELECT id FROM alarm ORDER BY id ASC")
    suspend fun getIds(): List<Int>

}