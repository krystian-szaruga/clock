package com.olr261dn.clock.components.colors.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getPrimaryContainerColor(): Color {
    return MaterialTheme.colorScheme.primaryContainer.copy(0.9f)
}

@Composable
fun getOnPrimaryContainerColor(): Color {
    return MaterialTheme.colorScheme.onPrimaryContainer
}