package com.olr261dn.clock.utils.timeConverters.localDateTime

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeToStringWithPattern(
    time: LocalDateTime,
    private val pattern: String): BaseToStringConverter(time) {
    override fun convert(): String = time.format(DateTimeFormatter.ofPattern(pattern))

}
