package com.olr261dn.clock.components.colors.appBar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun getAddIconTint(): Color {
    return MaterialTheme.colorScheme.onSecondary
}