package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.timerInitializer

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState

class TimerInitializer<T: BaseState>{

    fun initialize(
        timers: List<T>,
        id: Int,
        updateTimers: (T) -> Unit,
        appendTimer: () -> Unit)
    {
        timers.find { it.id == id }?.let {
            updateTimers(it)
        } ?: appendTimer()
    }
}