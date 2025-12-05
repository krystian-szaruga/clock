package com.olr261dn.clock.screens.clock.components.analogClock.drawClock

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

fun DrawScope.drawClockHands(
    hours: Int, minutes: Int, seconds: Int,
    pointerSecColor: Color,
    pointerDefaultColor: Color,
    center: Offset,
    radius: Float

) {

    fun drawHand(color: Color, angle: Double, lengthFactor: Float, strokeWidth: Dp) {
        val handAngle = Math.toRadians(angle)
        val endOffset = Offset(
            center.x + radius * lengthFactor * cos(handAngle).toFloat(),
            center.y + radius * lengthFactor * sin(handAngle).toFloat()
        )
        drawLine(color, center, endOffset, strokeWidth = strokeWidth.toPx())
    }

    drawHand(
        pointerSecColor,
        seconds * 6 - 90.0,
        0.8f,
        4.dp)
    drawHand(
        pointerDefaultColor,
        minutes * 6 - 90.0,
        0.7f,
        6.dp)
    drawHand(
        pointerDefaultColor,
        ((hours % 12) * 30 + minutes / 2 - 90).toDouble(),
        0.5f,
        8.dp)
}