package com.olr261dn.clock.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.R
import com.olr261dn.clock.components.colors.timerColors.getTimerControllerButtonColor


@Composable
fun TimeControlButtons(
    isTimerRunning: Boolean,
    isTimerStopped: Boolean,
    onStart: () -> Unit = {},
    onStop: () -> Unit = {},
    onReset: () -> Unit = {}
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            CreateControlButton(
                enabled = !isTimerRunning,
                icon = R.drawable.baseline_play_arrow_24) {
                onStart()
            }
            CreateControlButton(
                enabled = isTimerRunning,
                icon = R.drawable.baseline_stop_24) {
                onStop()
            }
            CreateControlButton(
                enabled = isTimerRunning || isTimerStopped,
                icon = R.drawable.baseline_replay_24) {
                onReset()
            }
        }
    }
}


@Composable
fun CreateControlButton(
    icon: Int,
    size: Dp = 34.dp,
    description: String = "",
    enabled: Boolean,
    onClick: () -> Unit = {}
) {
    Button(
        enabled = enabled,
        onClick = { onClick.invoke() },
        shape = CircleShape,
        colors = getTimerControllerButtonColor()
    ) {
        Icon(
            modifier = Modifier
                .size(size),
            painter = painterResource(id = icon),
            contentDescription = description)
    }
}