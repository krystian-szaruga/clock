package com.olr261dn.clock.utils.timeConverters.localDateTime

import java.time.LocalDateTime
import java.time.ZoneId

class TimeToLong(private val time: LocalDateTime) {
    fun convert(): Long = time.atZone(
        ZoneId.systemDefault()).toInstant().toEpochMilli()
}