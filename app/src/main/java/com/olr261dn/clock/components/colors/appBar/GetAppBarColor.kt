package com.olr261dn.clock.components.colors.appBar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun getAppBarColor(isDarkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (isDarkTheme)
        Color(0xFF121C14)
    else
        Color(0x97819383)
}

@Composable
fun getOnAppBarColor(isDarkTheme: Boolean = isSystemInDarkTheme()): Color {

    return if (isDarkTheme)
        Color(0xFFFFFEFE)
    else
        Color(0xFD111111)
}