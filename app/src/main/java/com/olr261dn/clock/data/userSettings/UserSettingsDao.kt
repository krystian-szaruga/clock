package com.olr261dn.clock.data.userSettings

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olr261dn.clock.model.UserSettings
import com.olr261dn.clock.screens.timer.components.utils.TimerDuration

@Dao
interface UserSettingsDao {

    @Query("SELECT * FROM user_settings WHERE id=1")
    suspend fun getUserSettings(): UserSettings

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserSettings(userSettings: UserSettings)

    @Query("UPDATE user_settings SET duration = :duration WHERE id = 1")
    suspend fun updateDuration(duration: TimerDuration)

    @Query("UPDATE user_settings SET show_analog_clock = :flag WHERE id = 1")
    suspend fun updateShowAnalogClock(flag: Boolean)

    @Query("SELECT COUNT(*) FROM user_settings")
    suspend fun getRowCount(): Int

    @Query("UPDATE user_settings SET last_screen_name = :lastScreenName WHERE id = 1")
    suspend fun updateLastScreen(lastScreenName: String)

}

