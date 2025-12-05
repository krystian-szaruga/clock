package com.olr261dn.clock.components.colors.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getSecondaryContainerColor(): Color {
    return MaterialTheme.colorScheme.secondaryContainer
}


@Composable
fun getOnSecondaryContainerColor(): Color {
    return MaterialTheme.colorScheme.onSecondaryContainer
}
