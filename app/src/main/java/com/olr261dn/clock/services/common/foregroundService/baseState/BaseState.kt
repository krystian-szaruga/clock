package com.olr261dn.clock.services.common.foregroundService.baseState

import com.olr261dn.clock.services.common.foregroundService.counterTimerWrapper.CounterTimeWrapper

abstract class BaseState(
    open val isTimerRunning: Boolean,
    open val isTimerStopped: Boolean,
    open val counterWrapper: CounterTimeWrapper,
    open val totalTime: Long = 0,
    open val id : Int = 0)
