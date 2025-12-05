package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerDisplayer

import java.util.Locale

class TimerDisplay(private val timeMillis: Long) {

    fun getFormattedTime(format: String = "%02d:%02d:%02d"): String {
        return extractFromMillis().let { (hours, minutes, seconds, millis) ->
            if (format.contains("%03d")) {
                String.format(Locale.US, format, hours, minutes, seconds, millis)
            } else {
                String.format(Locale.US, format, hours, minutes, seconds)
            }
        }
    }

    private fun extractFromMillis(): Quadruple<Int, Int, Int, Int> {
        val hours = (timeMillis / (1000 * 60 * 60)).toInt()
        val minutes = ((timeMillis / (1000 * 60)) % 60).toInt()
        val seconds = ((timeMillis / 1000) % 60).toInt()
        val millis = (timeMillis % 1000).toInt()
        return Quadruple(hours, minutes, seconds, millis)
    }
}

