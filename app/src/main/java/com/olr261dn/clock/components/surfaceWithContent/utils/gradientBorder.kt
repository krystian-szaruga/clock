package com.olr261dn.clock.components.surfaceWithContent.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

fun Modifier.gradientBorder(
    colors: List<Color>,
    borderWidth: Float,
    cornerRadius: Dp
) = this.drawBehind {
    val stroke = Stroke(width = borderWidth)
    val gradientBrush = Brush.horizontalGradient(colors)
    val cornerRadiusPx = cornerRadius.toPx()

    drawRoundRect(
        brush = gradientBrush,
        topLeft = Offset.Zero,
        size = Size(size.width, size.height),
        style = stroke,
        cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
    )
}