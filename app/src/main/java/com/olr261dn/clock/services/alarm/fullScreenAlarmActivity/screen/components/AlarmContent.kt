package com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.utils.SurfaceWithContent
import com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen.components.utils.getColorForOffset
import com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.screen.components.utils.getImageForOffset
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import kotlin.math.roundToInt

@Composable
fun AlarmContent(
    time: String,
    scale: Float,
    color: Color,
    onSnooze: () -> Unit,
    onDismiss: () -> Unit,
    onTargetColorChange: (Color) -> Unit) {

    var offset by remember { mutableFloatStateOf(0f) }
    val animatedOffset by animateFloatAsState(
        targetValue = offset,
        animationSpec = tween(
            durationMillis = 120,
            easing = FastOutSlowInEasing
        ),
        label = "Offset")
    val swipeThreshold = 100f

    val imageId = getImageForOffset(animatedOffset, swipeThreshold)
    onTargetColorChange(
        getColorForOffset(
            offset = animatedOffset,
            threshold = swipeThreshold)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(0.1f))
            .draggable(
                orientation = Orientation.Vertical, // Change orientation to Vertical
                state = rememberDraggableState { delta ->
                    offset = (offset + delta)
                        .coerceIn(-swipeThreshold-10, swipeThreshold+10)
                },
                onDragStopped = {
                    if (offset >= swipeThreshold) {
                        onSnooze()
                    } else if (offset <= -swipeThreshold) {
                        onDismiss()
                    }
                    offset = 0f
                })
            .offset { IntOffset(0, offset.roundToInt()) },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SurfaceWithContent(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(0.95f)
        ) {
            Text(
                text = time,
                color = color,
                fontSize = 70.nonScaledSp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        AlarmController(
            text = "DISMISS",
            color = color,
            scale = scale)
        SurfaceWithContent {
            Image(
                imageVector = ImageVector.vectorResource(id = imageId),
                contentDescription = "Alarm Indicator",
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(getBackgroundColor())
                    .size(160.dp)
                    .fillMaxWidth(0.85f)
                    .scale(scale)
            )
        }
        AlarmController(
            text = "SNOOZE",
            color = color,
            scale = scale
        )
    }
}


@Composable
private fun AlarmController(
    text: String,
    color: Color,
    scale: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth())
    {
        if (text.lowercase() == "snooze")
            Icon(
                modifier = Modifier.size(48.dp).scale(scale),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Swipe down",
                tint = color
            )
        SurfaceWithContent(
            modifier = Modifier
                .fillMaxWidth(0.9f),
        ) {
            Text(
                color = color,
                modifier = Modifier.scale(scale),
                textAlign = TextAlign.Center,
                text = text,
                fontSize = 55.nonScaledSp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        }
        if (text.lowercase() == "dismiss")
            Icon(
                modifier = Modifier.size(48.dp).scale(scale),
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Swipe up",
                tint = color
            )
    }
}
