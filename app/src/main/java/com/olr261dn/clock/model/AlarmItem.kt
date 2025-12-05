package com.olr261dn.clock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDateTime

@Entity(tableName = "alarm")
data class AlarmItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "time")
    val time: LocalDateTime,
    @ColumnInfo(name = "previous_time")
    val snoozeTime: LocalDateTime = time,
    @ColumnInfo(name = "label")
    val label: String = "Alarm",
    @ColumnInfo(name = "is_enabled")
    val isEnabled: Boolean = true,
    @ColumnInfo(name = "repeat_days")
    val repeatDays: Set<DayOfWeek> = setOf(),
    @ColumnInfo(name = "created_date")
    val createdDate: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "is_recurring")
    val isRecurring: Boolean = repeatDays.isNotEmpty(),
    @ColumnInfo(name = "is_snooze")
    val isSnooze : Boolean = false,
    @ColumnInfo(name = "is_single_occurrence")
    val isSingleOccurrence: Boolean = false
)
