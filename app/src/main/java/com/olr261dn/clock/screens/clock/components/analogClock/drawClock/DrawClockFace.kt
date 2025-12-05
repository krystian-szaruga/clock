package com.olr261dn.clock.screens.clock.components.analogClock.drawClock

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

fun DrawScope.drawClockFace(
    contentColor: Color,
    pointColor: Color,
    center: Offset,
    radius: Float
) {
    drawCircle(contentColor, radius, center)
    drawCircle(pointColor, 4.dp.toPx(), center)

    val paint = Paint().apply {
        color = pointColor.toArgb()
        textAlign = android.graphics.Paint.Align.CENTER
        textSize = 18.sp.toPx()
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    val fontMetrics = paint.fontMetrics

    (1..12).forEach { hour ->
        val angle = Math.toRadians((hour * 30 - 90).toDouble())
        val x = center.x + (radius - 20.dp.toPx()) * cos(angle)
        val y = center.y + (radius - 20.dp.toPx()) * sin(angle)

        val textY = y - (fontMetrics.ascent + fontMetrics.descent) / 2

        drawContext.canvas.nativeCanvas.drawText(
            hour.toString(),
            x.toFloat(),
            textY.toFloat(),
            paint
        )
    }
}
