package com.olr261dn.clock.services.stopwatch.foregroundService.countUpTimerWrapper

import android.os.SystemClock
import com.olr261dn.clock.services.common.foregroundService.counterTimerWrapper.CounterTimeWrapper

class CountUpTimerWrapper(private val maxTime: Long = 86400000L) : CounterTimeWrapper() {
    override fun setInitialValues() {
        startTime = if (time == 0L) SystemClock.elapsedRealtime() else
            SystemClock.elapsedRealtime() - time
        lastUpdateTime = 0L
    }

    override fun newTimerValue(): Long = elapsed

    override fun shouldUpdateForegroundNotifications(): Boolean =
        getSecondFromMillis(elapsed) > getSecondFromMillis(lastUpdateTime)

    override fun shouldEndTimer(): Boolean = elapsed >= maxTime

}
