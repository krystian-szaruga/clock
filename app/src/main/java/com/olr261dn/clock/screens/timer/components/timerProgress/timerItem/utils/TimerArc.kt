package com.olr261dn.clock.screens.timer.components.timerProgress.timerItem.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.R
import com.olr261dn.clock.components.buttons.CreateControlButton
import com.olr261dn.clock.components.colors.timerColors.getArcColor
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState
import com.olr261dn.clock.services.timer.foregroundService.viewModel.TimerViewModel
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TimerArc(
    timer: TimerState,
    id: Int,
    timerViewModel: TimerViewModel,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AdjustTimeOnGoButtons(
            timer = timer,
            id = id,
            timerViewModel = timerViewModel)
    }
    CreateArc(
        id = id,
        timer = timer) {
        onClick(it)
    }
}

@Composable
private fun AdjustTimeOnGoButtons(
    timer: TimerState,
    id: Int,
    timerViewModel: TimerViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val oneMinute = 60000L
    var isButtonDisabled = remember { mutableStateOf(false) }

    val modifyTimeOnGo: (Boolean) -> Unit = {
        val newTime = if (it) timer.timeLeft + oneMinute else
            timer.timeLeft - oneMinute
        coroutineScope.launch {
            timerViewModel.modifyTimeOnGo(newTime, id)
            isButtonDisabled.value = true
            delay(1500)
            isButtonDisabled.value = false
        }
    }

    AdjustTimeOnGo(
        text = "-1",
        enabled = timer.isTimerRunning &&
                timer.timeLeft >= oneMinute + 5000L &&
                !isButtonDisabled.value
    ) {
        modifyTimeOnGo(false)
    }

    CreateControlButton(
        icon = R.drawable.outline_delete_24,
        size = 28.dp,
        description = "Delete Timer",
        enabled = true ){
        timerViewModel.removeTimer(timer.id)
    }

    AdjustTimeOnGo(
        text = "+1",
        enabled = timer.isTimerRunning && !isButtonDisabled.value
    ) {
        modifyTimeOnGo(true)
    }
}

@Composable
private fun CreateArc(
    id: Int,
    timer: TimerState,
    onClick: (Int) -> Unit)
{
    val arcColor = getArcColor()
    val timeParts = timer.timeFormatted.split(":").map { it.toInt() }
    val hours = timeParts[0]
    val minutes = timeParts[1]
    val seconds = timeParts[2]

    val timeString = when {
        hours > 0 -> "$hours hour${if (hours > 1) "s" else ""} ${if (minutes > 0) "$minutes minute${if (minutes > 1) "s" else ""} " else ""}${if (seconds > 0) "$seconds second${if (seconds > 1) "s" else ""}" else ""}"
        minutes > 0 -> "$minutes minute${if (minutes > 1) "s" else ""} ${if (seconds > 0) "$seconds second${if (seconds > 1) "s" else ""}" else ""}"
        seconds > 0 -> "$seconds second${if (seconds > 1) "s" else ""}"
        else -> "Time's up!"
    }
    Box(
        modifier = Modifier
            .padding(20.dp)
            .clickable {
                onClick(id)
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(240.dp)) {
            drawArc(
                color = arcColor,
                startAngle = -90f,
                sweepAngle = 360 * timer.timePercentage,
                useCenter = false,
                style = Stroke(
                    width = 16.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = timer.timeFormatted, fontSize = 55.nonScaledSp)
            Text(timeString, fontSize = 12.nonScaledSp)
        }
    }
}
