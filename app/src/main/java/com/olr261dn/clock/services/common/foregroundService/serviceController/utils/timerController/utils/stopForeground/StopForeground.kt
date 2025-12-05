package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.stopForeground

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState

class StopForeground<T: BaseState>{

    fun stop(
        timer: T,
        updateTimers: (T) -> Unit = {})
    {
        timer.counterWrapper.stop()
        updateTimers(timer)
    }
}