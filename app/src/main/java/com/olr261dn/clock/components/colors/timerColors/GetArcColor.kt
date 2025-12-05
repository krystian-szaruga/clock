package com.olr261dn.clock.components.colors.timerColors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getArcColor(isDarkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (isDarkTheme){
        Color(0xFF2F3B25)
    } else {
        Color(0xFF98A291)
    }
}