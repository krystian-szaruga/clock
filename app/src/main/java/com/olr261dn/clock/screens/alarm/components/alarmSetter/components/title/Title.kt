package com.olr261dn.clock.screens.alarm.components.alarmSetter.components.title

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor

@Composable
fun Title() {
    Text(
        text = "Set Alarm",
        color = getOnBackgroundColor(),
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
    )
}