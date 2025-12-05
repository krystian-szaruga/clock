package com.olr261dn.clock.components.colors.alarmScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getDropdownBackgroundColor(): Color {
    return Color.Transparent
}

@Composable
fun getDropdownTextColor(): Color {
    return MaterialTheme.colorScheme.onBackground
}