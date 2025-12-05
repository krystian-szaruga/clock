package com.olr261dn.clock.services.widget.composable.components

import android.widget.RemoteViews
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.AndroidRemoteViews
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.size
import androidx.glance.layout.width
import com.olr261dn.clock.R
import com.olr261dn.clock.services.widget.composable.utils.CreateBox

@Composable
fun UpcomingAlarm(time: String, packageName: String) {
    CreateBox(
        topPadding = 1.dp,
        startPadding = 15.dp,
        endPadding = 15.dp,
        bottomPadding = 1.dp,
        cornerRadius = 10.dp,
        background = Color.Black.copy(0.4f)
    ) {
        val remoteViews = RemoteViews(packageName, R.layout.next_alarm_view)
        remoteViews.setTextViewText(R.id.alarmTextView, time
            .replace("am", "AM")
            .replace("pm", "PM"))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                provider = ImageProvider(R.drawable.baseline_alarm_24),
                contentDescription = "Alarm Icon",
                colorFilter = ColorFilter.tint(ColorProvider(
                    day = Color(0xFFE4B169),
                    night = Color(0xFFE4B169))),
                modifier = GlanceModifier
                    .size(20.dp)
            )
            Spacer(modifier = GlanceModifier.width(8.dp))
            AndroidRemoteViews(remoteViews)
        }
    }
}
