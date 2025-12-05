package com.olr261dn.clock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.timeFormatType.TimeFormatType

@Entity(tableName = "global_settings")
data class GlobalSettings(
    @PrimaryKey
    val id: Int = 1,
    @ColumnInfo(name = "early_alarm_reminder")
    val earlyAlarmReminder: Boolean = true,
    @ColumnInfo(name = "show_alarm_info")
    val showAlarmInfo: Boolean = true,
    @ColumnInfo(name = "snooze_time")
    val snoozeTime: String = "10 min",
    @ColumnInfo(name = "time_format")
    val timeFormat: String = TimeFormatType.SYSTEM,
    @ColumnInfo(name = "increase_volume_gradually")
    val increaseVolumeGradually: Boolean = true,
    @ColumnInfo(name = "display_seconds_in_widget")
    val displaySecondsInWidget: Boolean = false
)
