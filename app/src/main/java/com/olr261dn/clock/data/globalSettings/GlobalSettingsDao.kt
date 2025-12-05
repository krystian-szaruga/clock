package com.olr261dn.clock.data.globalSettings

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olr261dn.clock.model.GlobalSettings

@Dao
interface GlobalSettingsDao {

    @Query("SELECT * FROM global_settings WHERE id=1")
    suspend fun getGlobalSettings(): GlobalSettings

    @Query("SELECT COUNT(*) FROM global_settings")
    suspend fun getRowCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGlobalSettings(globalSettings: GlobalSettings)

    @Query("UPDATE global_settings SET early_alarm_reminder = :earlyAlarmReminder WHERE id = 1")
    suspend fun updateEarlyAlarmReminder(earlyAlarmReminder: Boolean)

    @Query("UPDATE global_settings SET show_alarm_info = :showAlarmInfo WHERE id = 1")
    suspend fun updateShowAlarmInfo(showAlarmInfo: Boolean)

    @Query("UPDATE global_settings SET snooze_time = :snoozeTime WHERE id = 1")
    suspend fun updateSnoozeTime(snoozeTime: String)

    @Query("UPDATE global_settings SET time_format = :timeFormat WHERE id = 1")
    suspend fun updateTimeFormat(timeFormat: String)

    @Query("UPDATE global_settings SET increase_volume_gradually = :value WHERE id = 1")
    suspend fun updateIncreaseVolumeGradually(value: Boolean)

    @Query("UPDATE global_settings SET display_seconds_in_widget = :value WHERE id = 1")
    suspend fun updateDisplaySecondsInWidget(value: Boolean)
}

