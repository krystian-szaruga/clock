package com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.scaffoldBar.topBar.AppBarTop
import com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen.components.AlarmContent


@Composable
fun AlarmOnLockScreen(
    label: String,
    time: String,
    onDismiss: () -> Unit,
    onSnooze: () -> Unit) {

    val infiniteTransition = rememberInfiniteTransition(label = label)
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = label)

    var targetColor by remember { mutableStateOf(Color(0xFF7C0202)) }
    val color by infiniteTransition.animateColor(
        initialValue = getOnBackgroundColor(),
        targetValue = targetColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "Animation Color")

    Scaffold(topBar = { AppBarTop(title = "Alarm") {} }) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(getBackgroundColor())) {
            AlarmContent(
                time = time,
                scale = scale,
                color = color,
                onSnooze = onSnooze,
                onDismiss = onDismiss){ targetColor = it }

        }
    }
}









