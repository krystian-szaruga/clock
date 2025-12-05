package com.olr261dn.clock.services.widget.composable.components

import android.widget.RemoteViews
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.AndroidRemoteViews
import androidx.glance.layout.fillMaxWidth
import com.olr261dn.clock.R
import com.olr261dn.clock.components.colors.widget.getOnWidgetColor
import com.olr261dn.clock.services.widget.composable.utils.CreateBox

@Composable
fun ActualTimeBox(timePattern: String, packageName: String) {
    CreateBox(
        cornerRadius = 20.dp,
        modifier = GlanceModifier
            .fillMaxWidth(),
        background = getOnWidgetColor()
    ) {
        val layoutId = if (timePattern.contains("ss")) {
            R.layout.text_clock_with_seconds
        } else {
            R.layout.text_clock
        }
        val remoteViews = RemoteViews(packageName, layoutId)
        remoteViews.setCharSequence(R.id.textClock, "setFormat12Hour", timePattern)
        remoteViews.setCharSequence(R.id.textClock, "setFormat24Hour", timePattern)
        AndroidRemoteViews(remoteViews = remoteViews)
    }
}
