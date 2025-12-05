package com.olr261dn.clock.screens.timer.components.timerProgress.timerItem.utils

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.olr261dn.clock.components.colors.timerColors.getTimerControllerButtonColor
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@Composable
fun AdjustTimeOnGo(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit) {

    TextButton(
        enabled = enabled,
        onClick = { onClick() },
        colors = getTimerControllerButtonColor())
    {
        Text(
            text,
            fontSize = 28.nonScaledSp,
            fontWeight = FontWeight.ExtraBold)
    }
}
