package com.olr261dn.clock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.screens.timer.components.utils.TimerDuration

@Entity(tableName = "user_settings")
data class UserSettings(
    @PrimaryKey
    val id: Int = 1,
    @ColumnInfo(name = "show_analog_clock")
    val showAnalogClock: Boolean = false,
    @ColumnInfo(name = "duration")
    val timerDuration: TimerDuration = TimerDuration(),
    @ColumnInfo(name = "last_screen_name")
    val lastScreenName: String = ClockScreens.Clock.name)
