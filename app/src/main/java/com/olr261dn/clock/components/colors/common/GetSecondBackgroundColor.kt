package com.olr261dn.clock.components.colors.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun getSecondBackgroundColor(): Color {
    val secondBackgroundColor = Color(0xFF538353)
    return if (isSystemInDarkTheme())
        secondBackgroundColor.copy(0.15f)
    else
        secondBackgroundColor.copy(0.35f)
}
