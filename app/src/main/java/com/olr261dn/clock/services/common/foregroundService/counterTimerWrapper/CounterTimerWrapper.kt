package com.olr261dn.clock.services.common.foregroundService.counterTimerWrapper

import android.os.SystemClock
import android.util.Log
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

abstract class CounterTimeWrapper {
    protected var lastUpdateTime: Long = 0L
    protected var startTime: Long = 0L
    protected var elapsed = 0L
    protected var time = 0L
    private var scheduler: ScheduledExecutorService? = null
    private var task: Runnable? = null
    private var isRunning = false
    private var onFinishAction : () -> Unit = {}
    private var onUpdateTimer : (Long) -> Unit = {}
    private var onUpdateForeground : () -> Unit = {}

    fun start(
        time: Long,
        onFinishAction: () -> Unit,
        updateTimer: (Long) -> Unit,
        updateForeground: () -> Unit
    ) {
        this.time = time
        this.onFinishAction = onFinishAction
        this.onUpdateTimer = updateTimer
        this.onUpdateForeground = updateForeground
        setTimer()
        launchTimer()
    }

    fun stop() {
        isRunning = false
        lastUpdateTime = 0L
        scheduler?.shutdownNow() ?: logOnNull()
        scheduler = null
        task = null
    }

    private fun setTimer() {
        resetAndStartTimer()
        setInitialValues()
        setTask()

    }

    private fun launchTimer() {
        scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler?.scheduleWithFixedDelay(task, 0, 20, TimeUnit.MILLISECONDS) ?: logOnNull()
    }

    private fun resetAndStartTimer() {
        stop()
        isRunning = true
    }

    protected abstract fun setInitialValues()

    private fun setTask() {
        task = Runnable {
            if (!isRunning) return@Runnable
            setElapsed()
            updateTimer()
            updateForegroundNotification()
            finishIfTimeReached()
        }
    }

    private fun setElapsed() {
        elapsed = SystemClock.elapsedRealtime() - startTime
    }

    private fun updateTimer() {
        onUpdateTimer(newTimerValue())
    }

    protected abstract fun newTimerValue(): Long

    private fun updateForegroundNotification() {
        if (shouldUpdateForegroundNotifications()) {
            onUpdateForeground()
            updateLastUpdateTime()
        }
    }

    protected abstract fun shouldUpdateForegroundNotifications(): Boolean

    private fun updateLastUpdateTime() {
        lastUpdateTime = newTimerValue()
    }

    private fun finishIfTimeReached() {
        if (shouldEndTimer()) {
            onFinishAction()
            stop()
        }
    }

    protected fun getSecondFromMillis(value: Long): Long = value / 1000;

    protected abstract fun shouldEndTimer(): Boolean

    private fun logOnNull() {
        Log.d("CountUpTimerWrapper", "Choreographer is null")
    }
}
