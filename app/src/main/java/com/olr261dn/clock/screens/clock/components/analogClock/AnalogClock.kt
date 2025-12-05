package com.olr261dn.clock.screens.clock.components.analogClock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.clockScreen.getAnalogClockColors
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.screens.clock.components.analogClock.drawClock.drawClockFace
import com.olr261dn.clock.screens.clock.components.analogClock.drawClock.drawClockHands
import java.time.ZonedDateTime
import kotlin.math.min


@Composable
fun AnalogClock(
    currentTime: State<ZonedDateTime>,
    showContent: Boolean,
    buttonContent: @Composable () -> Unit)
{
    val (background, points, pointerSecColor,
        pointerDefaultColor) = getAnalogClockColors()

    SurfaceWithColumn {
        buttonContent.invoke()
        Spacer(modifier = Modifier.height(10.dp))
        if (showContent) {
            Canvas(modifier = Modifier.size(250.dp)) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val center = Offset(canvasWidth / 2, canvasHeight / 2)
                val radius = min(canvasWidth, canvasHeight) / 2
                drawClockFace(
                    contentColor = background,
                    pointColor = points,
                    center = center,
                    radius = radius
                )
                drawClockHands(
                    hours = currentTime.value.hour,
                    minutes = currentTime.value.minute,
                    seconds = currentTime.value.second,
                    pointerDefaultColor = pointerDefaultColor,
                    pointerSecColor = pointerSecColor,
                    center = center,
                    radius = radius
                )
            }
        }
    }
}
