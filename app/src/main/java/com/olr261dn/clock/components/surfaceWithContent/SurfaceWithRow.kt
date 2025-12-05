package com.olr261dn.clock.components.surfaceWithContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.utils.SurfaceWithContent

@Composable
fun SurfaceWithRow(
    modifier: Modifier = Modifier,
    padding: Dp = 10.dp,
    borderWidth: Float = 8f,
    fraction: Float = 1.0f,
    roundedCornerDp: Dp = 30.dp,
    customBackgroundColor: Color = getBackgroundColor(),
    composableContent: @Composable () -> Unit,
){

    SurfaceWithContent(
        padding = padding,
        fraction = fraction,
        modifier = modifier,
        roundedCornerDp = roundedCornerDp,
        borderWidth = borderWidth,
        customBackgroundColor = customBackgroundColor)
    {
        Row (
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(fraction),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            composableContent.invoke()
        }
    }
}