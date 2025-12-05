package com.olr261dn.clock.services.widget.composable.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.padding

@Composable
fun CreateBox(
    modifier: GlanceModifier = GlanceModifier,
    startPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    topPadding: Dp = 0.dp,
    cornerRadius: Dp = 20.dp,
    background: Color,
    composable: @Composable () -> Unit)
{
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(
                bottom = bottomPadding,
                top = topPadding,
                start = startPadding,
                end = endPadding)
            .cornerRadius(cornerRadius)
            .background(background)) { composable.invoke() }
}