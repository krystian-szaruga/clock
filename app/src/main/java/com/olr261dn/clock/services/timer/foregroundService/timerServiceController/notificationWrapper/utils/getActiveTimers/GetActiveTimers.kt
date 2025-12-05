package com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper.utils.getActiveTimers

import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState


class GetActiveTimers{

    fun getActiveTimers(timers: List<TimerState>): List<TimerState> {
        return timers.filter { it.isTimerRunning || it.isTimerStopped }
    }
}