package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.timerPauseController

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState

class TimerPauseController<T: BaseState>{
    fun pause(
        timer: T,
        updateTimers: (T) -> Unit) {
        if (!timer.isTimerStopped){
            timer.counterWrapper.stop()
            updateTimers(timer)
        }
    }
}