package com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper.utils.notificationContent

import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerDisplayer.TimerDisplay
import com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper.utils.getActiveTimers.GetActiveTimers
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState

class NotificationContent {

    private val getActiveTimers = GetActiveTimers()

    fun buildTimerText(timers: List<TimerState>): List<String> {
        val activeTimers = getActiveTimers.getActiveTimers(timers)
        return if (activeTimers.isNotEmpty()) {
            activeTimers.map {
                "Timer ${it.id + 1}: ${TimerDisplay(it.timeLeft).getFormattedTime()}"
            }
        } else {
            emptyList()
        }
    }
}