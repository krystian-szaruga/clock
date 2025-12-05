package com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen.components.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getColorForOffset(offset: Float, threshold: Float): Color {
    return when {
        offset >= threshold -> Color.Yellow
        offset <= -threshold -> Color.Green
        else -> Color(0xFF7C0202)
    }
}