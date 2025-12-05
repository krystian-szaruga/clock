package com.olr261dn.clock.services.timer.foregroundService.timerServiceController

import com.olr261dn.clock.services.common.foregroundService.serviceController.BaseServiceController
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerDisplayer.TimerDisplay
import com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper.TimerStateNotificationWrapper
import com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper.utils.notificationContent.NotificationContent
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState
import kotlinx.coroutines.flow.MutableStateFlow


class TimerServiceController(foregroundDisabler: () -> Unit):
    BaseServiceController<
            TimerStateNotificationWrapper,
            TimerState>(foregroundDisabler) {
    val timers = MutableStateFlow<List<TimerState>>(emptyList())
    private val notificationContent = NotificationContent()

    override fun buildNotificationText(): Pair<List<String>, String> =
        Pair(notificationContent.buildTimerText(timers.value), "")

    private fun updateTimers(newTimer: TimerState){
        timers.value = timers.value.map { timerState ->
            if (timerState.id == newTimer.id) newTimer else timerState
        }
    }

    fun removeTimer(id: Int) {
        resetTimer(id)
        timers.value = timers.value.filter { timerState ->
            timerState.id != id
        }
    }

    private fun getTimer(id: Int): TimerState {
        return timers.value.first { it.id == id }
    }

    private fun stopForeground(timer: TimerState) {
        timerController.stopForeground.stop(timer = timer) {
            updateTimers(it.copy(
                totalTime = timer.initialTotalTime,
                timeLeft = timer.initialTotalTime,
                timePercentage = 1f,
                timeFormatted = TimerDisplay(timer.initialTotalTime)
                    .getFormattedTime(),
                isTimerStopped = false,
                isTimerRunning = false)) }
        disableForeground()
    }

    private fun disableForeground() {
        notificationWrapper?.dismissForegroundNotification(timers.value) {
            if (it) { foregroundDisabler() }
        }
    }

    fun startTimer(id: Int) {
        val timer = getTimer(id)
        timerController.timerLauncher.launch(
            timer = timer,
            updateForeground = {
                notificationWrapper
                    ?.updateForegroundNotification(
                        buildNotificationText()) },
            updateTimers = { millis ->
                updateTimers(timer.copy(
                    timeFormatted = TimerDisplay(millis)
                        .getFormattedTime(),
                    timePercentage = if (millis <= 1000L) 0f else
                        (millis - 1000).toFloat() / timer.totalTime.toFloat(),
                    timeLeft = millis,
                    isTimerRunning = true,
                    isTimerStopped = false)) },
            stopForeground = {
                stopForeground(it)
                notificationWrapper?.updateForegroundNotification(buildNotificationText())
                notificationWrapper?.dismissForegroundNotification(timers.value) {
                    if (it) { foregroundDisabler() }
                }
            }
        )
    }

    fun stopTimer(id: Int){
        timerController.timerPauseController.pause(getTimer(id)){
            updateTimers(it.copy(
                isTimerStopped = true,
                isTimerRunning = false))
        }
    }

    private fun resetTimer(id: Int) {
        val timer = getTimer(id)
        stopForeground(timer)
    }

    fun initializeData(
        time: Long, id: Int, updateFlag: Boolean = true, updateFromStop: Boolean = false) {
        val timer = TimerState(
            id = id,
            initialTotalTime = time,
            totalTime = time,
            timeLeft = time,
            timeFormatted = TimerDisplay(time)
                .getFormattedTime())

        timerController.timerInitializer.initialize(
            timers = timers.value,
            id = id,
            updateTimers = {
                if (updateFlag){
                    updateTimers(it.copy(
                        initialTotalTime = if (updateFromStop) it.initialTotalTime else timer.initialTotalTime,
                        totalTime = timer.totalTime,
                        timeLeft = timer.timeLeft,
                        timeFormatted = timer.timeFormatted))
                } },
            appendTimer = {timers.value += timer})
    }

    fun modifyTimeOnGo(time: Long, id: Int){
        initializeData(time - 1000L, id, updateFromStop = true)
        startTimer(id)
    }
}
