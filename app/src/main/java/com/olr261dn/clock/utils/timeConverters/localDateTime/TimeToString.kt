package com.olr261dn.clock.utils.timeConverters.localDateTime

import android.content.Context
import com.olr261dn.clock.utils.timeConverters.timeFormatProvider.TimeFormatProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeToString(
    private val context: Context,
    time: LocalDateTime): BaseToStringConverter(time)
{
    override fun convert(): String {
        return time.format(
            DateTimeFormatter.ofPattern(
                TimeFormatProvider(context.applicationContext).getTimeFormatPattern()
            )
        )
    }
}
