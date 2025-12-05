package com.olr261dn.clock.screens.timer.components.timerProgress.timerItem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.buttons.TimeControlButtons
import com.olr261dn.clock.screens.timer.components.timerProgress.timerItem.utils.TimerArc
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState
import com.olr261dn.clock.services.timer.foregroundService.viewModel.TimerViewModel
import java.time.Instant

@Composable
fun TimerItem(
    id: Int,
    timer: TimerState,
    timerViewModel: TimerViewModel,
    onClick: (Int) -> Unit)
{
    TimerArc(
        timer = timer,
        id = id,
        timerViewModel = timerViewModel) { onClick(it) }

    TimeControlButtons(
        isTimerStopped = timer.isTimerStopped,
        isTimerRunning = timer.isTimerRunning,
        onStart = {
            val now = Instant.now()
            val triggerTime = when {
                timer.isTimerStopped -> {
                    timerViewModel.initTimeLeft(timer.timeLeft - 1000L, id, updateFromStop = true)
                    now.plusMillis(timer.timeLeft).toEpochMilli()
                }
                else -> {
                    timerViewModel.initTimeLeft(timer.totalTime, id)
                    now.plusMillis(timer.totalTime).toEpochMilli()
                }
            }
            timerViewModel.startTimer(triggerTime, id)
        },
        onStop = {
            timerViewModel.stopTimer(id)
        },
        onReset = {
            timerViewModel.resetTimer(id)
            timerViewModel.initTimeLeft(timer.initialTotalTime, id)
        }
    )
    Spacer(modifier = Modifier.height(10.dp))
}



