package com.olr261dn.clock.services.timer.foregroundService.timerState

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState
import com.olr261dn.clock.services.timer.countDownTimer.CountDownTimerWrapper


data class TimerState(
    override val id: Int = 0,
    override val isTimerRunning: Boolean = false,
    override val isTimerStopped: Boolean = false,
    val timeLeft: Long = 0L,
    val initialTotalTime: Long = 0L,
    override val totalTime: Long = 0L,
    val timePercentage: Float = 1.0f,
    val timeFormatted: String = "00:00:00",
    override val counterWrapper: CountDownTimerWrapper =
        CountDownTimerWrapper()
) : BaseState(isTimerRunning = isTimerRunning,
        isTimerStopped = isTimerStopped, counterWrapper = counterWrapper,
        totalTime = totalTime, id = id)
