package com.olr261dn.clock.components.colors.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getThirdBackgroundColor(): Color {
    return if (isSystemInDarkTheme())
        Color(0xFF252D29)
    else
        Color(0xFFD4DED6)
}