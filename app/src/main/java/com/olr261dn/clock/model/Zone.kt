package com.olr261dn.clock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zone_ids")
data class Zone(
    @PrimaryKey
    @ColumnInfo(name = "zone_id")
    val zoneId: String)
