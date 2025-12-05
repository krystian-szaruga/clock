package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.stopForeground.StopForeground
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.timerInitializer.TimerInitializer
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.timerLauncher.TimerLauncher
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.utils.timerPauseController.TimerPauseController

class TimerController <T: BaseState> {
    val timerLauncher = TimerLauncher<T>()
    val timerPauseController = TimerPauseController<T>()
    val timerInitializer = TimerInitializer<T>()
    val stopForeground = StopForeground<T>()
}
