package com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen.components.utils

import androidx.compose.runtime.Composable
import com.olr261dn.clock.R

@Composable
fun getImageForOffset(offset: Float, threshold: Float): Int {
    return when {
        offset >= threshold -> R.drawable.baseline_snooze_24
        offset <= -threshold -> R.drawable.baseline_alarm_off_24
        else -> R.drawable.baseline_alarm_24
    }
}
