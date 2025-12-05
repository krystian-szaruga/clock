package com.olr261dn.clock.services.timer.countDownTimer

import android.os.SystemClock
import com.olr261dn.clock.services.common.foregroundService.counterTimerWrapper.CounterTimeWrapper

class CountDownTimerWrapper() : CounterTimeWrapper() {
    private var countdownTime: Long = 0
    private var timeLeft: Long = 0

    override fun setInitialValues() {
        countdownTime = time + 1000L
        startTime = SystemClock.elapsedRealtime()
        lastUpdateTime = countdownTime + 1000L
    }

    override fun newTimerValue(): Long {
        timeLeft = countdownTime - elapsed
        return timeLeft
    }

    override fun shouldUpdateForegroundNotifications(): Boolean =
        getSecondFromMillis(timeLeft) < getSecondFromMillis(lastUpdateTime)

    override fun shouldEndTimer(): Boolean = timeLeft <= 0
}
