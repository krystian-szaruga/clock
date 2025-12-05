package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.timerLauncher

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState

class TimerLauncher<T: BaseState> {
    fun launch(
        timer: T,
        updateForeground: () -> Unit,
        updateTimers: (Long) -> Unit,
        stopForeground: (T) -> Unit
    ) {
        timer.counterWrapper.start(
            time = timer.totalTime,
            onFinishAction = { stopForeground(timer) },
            updateTimer = { updateTimers(it) },
            updateForeground = { updateForeground() }
        )
    }
}
