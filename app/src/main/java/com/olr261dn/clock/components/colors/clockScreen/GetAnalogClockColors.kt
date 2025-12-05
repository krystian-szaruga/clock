package com.olr261dn.clock.components.colors.clockScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor


data class AnalogClockColors(
    val background: Color,
    val second: Color,
    val third: Color,
    val last: Color
)


@Composable
fun getAnalogClockColors(): AnalogClockColors {
    return AnalogClockColors(
        background = getSecondBackgroundColor(),
        second = getOnBackgroundColor(),
        third = Color.Red,
        last = Color.DarkGray
    )
}