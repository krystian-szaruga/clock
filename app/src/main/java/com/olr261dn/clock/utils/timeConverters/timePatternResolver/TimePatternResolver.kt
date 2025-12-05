package com.olr261dn.clock.utils.timeConverters.timePatternResolver

import android.util.Log
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.timeFormatType.TimeFormatType
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

class TimePatternResolver(
    private val timeType: String,
    private val displaySeconds: Boolean = false) {

    fun getFormat(): String {
        return when (timeType) {
            TimeFormatType.SYSTEM -> getSystemPattern()
            TimeFormatType.TWELVE_HOUR -> get12HourPattern()
            TimeFormatType.TWENTY_FOUR_HOUR -> get24HourPattern()
            else -> {
                Log.d("GetTimeFormat", "Invalid timeType '$timeType' provided." +
                        " Defaulting to 'System'")
                getSystemPattern()
            }
        }
    }

    private fun getSystemPattern(): String {
        val timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()) as SimpleDateFormat
        val basePattern = timeFormat.toPattern()
        val patternWithSeconds = addSecondsToPattern(basePattern)
        val patternWithoutSeconds = removeSecondsFromPattern(basePattern)
        return formatSystemPattern(
            if (displaySeconds) patternWithSeconds else patternWithoutSeconds)
    }

    private fun get12HourPattern(): String {
        val pattern = "hh:mm a"
        val patternWithSeconds = addSecondsToPattern(pattern)
        return if (displaySeconds) patternWithSeconds else pattern
    }

    private fun get24HourPattern(): String {
        val pattern = "HH:mm"
        val patternWithSeconds = addSecondsToPattern(pattern)
        return if (displaySeconds) patternWithSeconds else pattern
    }

    private fun addSecondsToPattern(pattern: String): String {
        return if (!pattern.contains("ss")) {
            pattern.replace(Regex("mm(?=[^a-zA-Z]*a|[^a-zA-Z]*\$)")) { matchResult ->
                "${matchResult.value}:ss"
            }
        } else {
            pattern
        }
    }

    private fun removeSecondsFromPattern(pattern: String): String {
        return pattern.replace("ss", "")
    }

    private fun formatSystemPattern(pattern: String): String {
        return pattern.replace(Regex("\\b([hH])\\b")) {
            when (it.value) {
                "h" -> "hh"
                "H" -> "HH"
                else -> it.value
            }
        }
    }
}