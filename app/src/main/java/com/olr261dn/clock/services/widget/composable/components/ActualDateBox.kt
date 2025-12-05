package com.olr261dn.clock.services.widget.composable.components

import android.widget.RemoteViews
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.AndroidRemoteViews
import com.olr261dn.clock.R
import com.olr261dn.clock.services.widget.composable.utils.CreateBox
import com.olr261dn.clock.services.widget.composable.utils.getCurrentDate

@Composable
fun ActualDateBox(packageName: String) {
    CreateBox(
        topPadding = 2.dp,
        startPadding = 20.dp,
        endPadding = 30.dp,
        bottomPadding = 2.dp,
        cornerRadius = 30.dp,
        background = Color.Black.copy())
    {
        val remoteViews = RemoteViews(packageName, R.layout.date_text_view)
        remoteViews.setTextViewText(R.id.dateTextView, getCurrentDate())
        AndroidRemoteViews(remoteViews = remoteViews)
    }
}