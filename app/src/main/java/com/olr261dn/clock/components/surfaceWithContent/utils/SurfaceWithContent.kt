package com.olr261dn.clock.components.surfaceWithContent.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getGradientsColors

@Composable
fun SurfaceWithContent(
    modifier: Modifier = Modifier,
    fraction: Float = 1.0f,
    padding: Dp = 10.dp,
    borderWidth: Float = 8f,
    roundedCornerDp: Dp = 30.dp,
    customBackgroundColor: Color = getBackgroundColor(),
    composableContent: @Composable () -> Unit,
){
    val updatedModifier = if (fraction > 0) modifier.fillMaxWidth(fraction) else modifier
    Surface(
        modifier = updatedModifier
            .padding(padding)
            .gradientBorder(
                borderWidth = borderWidth,
                colors = getGradientsColors(),
                cornerRadius = roundedCornerDp),
        shape = RoundedCornerShape(roundedCornerDp),
        color = customBackgroundColor)
    {
        composableContent.invoke()
    }
}