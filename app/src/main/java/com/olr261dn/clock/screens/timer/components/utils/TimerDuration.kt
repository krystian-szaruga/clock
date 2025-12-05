package com.olr261dn.clock.screens.timer.components.utils

data class TimerDuration(
    val hour: Int = 0,
    val minute: Int = 8,
    val second: Int = 0,
    val hourIndex: Int = hour + 2,
    val minuteIndex: Int = minute + 2,
    val secondIndex: Int = second + 2) {
    override fun toString(): String {
        return hour.toString().padStart(
            2, '0') +
                ":${minute.toString().padStart(
                    2, '0')}" +
                ":${second.toString().padStart(
                    2, '0')}"
    }

     fun calculateTotalMillis(): Long {
        val hoursInMillis = hour * 60 * 60 * 1000L
        val minutesInMillis = minute * 60 * 1000L
        val secondsInMillis = second * 1000L
        return hoursInMillis + minutesInMillis + secondsInMillis
    }


}
