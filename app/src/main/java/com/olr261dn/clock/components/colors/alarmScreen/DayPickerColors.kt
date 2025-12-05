package com.olr261dn.clock.components.colors.alarmScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getDefaultBoxColor(): Color {
    return MaterialTheme.colorScheme.background.copy(0.7f)
}

@Composable
fun getCheckedBoxColor(): Color {
    return MaterialTheme.colorScheme.secondaryContainer
}

@Composable
fun getOnDefaultBoxColor(): Color {
    return MaterialTheme.colorScheme.onBackground
}

@Composable
fun getOnCheckedBoxColor(): Color {
    return MaterialTheme.colorScheme.onSecondaryContainer
}