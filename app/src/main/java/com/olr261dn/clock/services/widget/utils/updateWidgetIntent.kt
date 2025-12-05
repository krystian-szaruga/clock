package com.olr261dn.clock.services.widget.utils

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.widget.receiver.TriggerWidgetUpdate

fun updateWidgetIntent(context: Context) {
    val intent = Intent(context, TriggerWidgetUpdate::class.java)
    context.sendBroadcast(intent)
}