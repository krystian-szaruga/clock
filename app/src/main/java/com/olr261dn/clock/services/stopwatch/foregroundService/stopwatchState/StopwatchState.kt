package com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchState

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState
import com.olr261dn.clock.services.stopwatch.foregroundService.countUpTimerWrapper.CountUpTimerWrapper

data class StopwatchState(
    override val totalTime: Long = 0L,
    val timeFormatted: String = "00:00:00",
    override val isTimerStopped: Boolean = false,
    override val isTimerRunning: Boolean = false,
    val laps : List<String> = emptyList(),
    override val counterWrapper: CountUpTimerWrapper =
        CountUpTimerWrapper()
) : BaseState(isTimerRunning=isTimerRunning, isTimerStopped =  isTimerStopped,
        counterWrapper = counterWrapper, totalTime=totalTime)
