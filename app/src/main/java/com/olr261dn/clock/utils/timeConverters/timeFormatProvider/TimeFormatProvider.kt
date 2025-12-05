package com.olr261dn.clock.utils.timeConverters.timeFormatProvider

import android.content.Context
import android.util.Log
import com.olr261dn.clock.utils.globalSettingsProvider.GlobalSettingsProvider
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.TimePatternResolver
import kotlinx.coroutines.runBlocking


class TimeFormatProvider (context: Context) {
    private val repository = GlobalSettingsProvider(context).getRepository()
    private var timeType = "System"

    fun getTimeFormatPattern(): String {
        getTimeType()
        Log.d("TimeFormatProvider", "timeType: $timeType")
        return TimePatternResolver(timeType).getFormat()
    }

    private fun getTimeType() {
        runBlocking {
            timeType = repository.getGlobalSettings().timeFormat
        }
    }
}

