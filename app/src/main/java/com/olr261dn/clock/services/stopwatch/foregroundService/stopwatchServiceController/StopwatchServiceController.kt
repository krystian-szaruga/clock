package com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchServiceController

import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerDisplayer.TimerDisplay
import com.olr261dn.clock.services.common.foregroundService.serviceController.BaseServiceController
import com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchServiceController.notificationWrapper.StopwatchStateNotificationWrapper
import com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchState.StopwatchState
import kotlinx.coroutines.flow.MutableStateFlow

class StopwatchServiceController(foregroundDisabler: () -> Unit):
    BaseServiceController<
            StopwatchStateNotificationWrapper,
            StopwatchState>(foregroundDisabler) {
    val stopwatch = MutableStateFlow(StopwatchState())

    private val timeFormat = "%02d:%02d:%02d.%03d"

    private fun stopForeground(){
        timerController.stopForeground.stop(stopwatch.value)
        notificationWrapper?.dismissForegroundNotification()
        foregroundDisabler()
    }

    override fun buildNotificationText(): Pair<List<String>, String> {
        val formattedStrings = stopwatch.value.laps.mapIndexed { idx, value ->
            "Lap ${idx+1}: $value"
        }
        return Pair(
            formattedStrings.reversed(),
            stopwatch.value.timeFormatted.substringBefore(".")
        )
    }

    fun startTimer(){
        timerController.timerLauncher.launch(
            timer = stopwatch.value,
            stopForeground = { stopForeground() },
            updateForeground = {
                notificationWrapper?.updateForegroundNotification(
                    buildNotificationText())
            },
            updateTimers = {
                stopwatch.value = stopwatch.value.copy(
                    timeFormatted = TimerDisplay(it)
                        .getFormattedTime(timeFormat),
                    totalTime = it,
                    isTimerRunning = true,
                    isTimerStopped = false)
            }
        )
    }

    fun stopTimer() {
        timerController.timerPauseController.pause(stopwatch.value) {
            stopwatch.value = stopwatch.value.copy(
                isTimerStopped = true,
                isTimerRunning = false
            )
        }
    }

    fun resetTimer(){
        stopForeground()
        stopwatch.value = stopwatch.value.copy(
            totalTime = 0,
            timeFormatted = TimerDisplay(0).getFormattedTime(),
            laps = listOf(),
            isTimerStopped = false,
            isTimerRunning = false
        )
    }

    fun getLap(){
        if (stopwatch.value.isTimerStopped) return
        val leap = TimerDisplay(stopwatch.value.totalTime)
            .getFormattedTime(format = timeFormat)
        stopwatch.value = stopwatch.value.copy(
            laps = stopwatch.value.laps + leap)
        notificationWrapper?.updateForegroundNotification(
            buildNotificationText()
        )
    }
}
