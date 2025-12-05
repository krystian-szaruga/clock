package com.olr261dn.clock.components.colors.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getWidgetColor(): Color {
    return Color(0xFF031412).copy(0.6f)
}

@Composable
fun getOnWidgetColor(): Color {
    return Color(0xFFFFFFFF)
}