package com.olr261dn.clock.screens.clock.components.gradientDivider

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getGradientsColors

@Composable
fun GradientDivider() {
    val infiniteTransition = rememberInfiniteTransition(label = "Divider")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0.15f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Divider")
    val colors = getGradientsColors()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Canvas(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .height(20.dp)
                .fillMaxWidth(0.9f)
                .shadow(4.dp, RoundedCornerShape(10.dp))
        ) {
            val lineY = size.height / 2
            drawRoundRect(
                brush = getGradient(
                    width = size.width, animatedOffset, colors),
                size = Size(size.width, size.height),
                topLeft = Offset(0f, lineY - 4.dp.toPx()),
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )
        }
    }
}

private fun getGradient(
    width: Float,
    animatedOffset: Float,
    colors: List<Color>): Brush
{
    return Brush.horizontalGradient(
        colors = colors,
        startX = animatedOffset,
        endX = (width * animatedOffset)
    )
}