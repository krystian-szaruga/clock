package com.olr261dn.clock.services.widget.receiver

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.olr261dn.clock.services.alarm.workers.utils.workerLauncher.WorkerLauncher
import com.olr261dn.clock.services.widget.ClockWidget
import com.olr261dn.clock.services.widget.workers.UpdateWidgetWorker


class ClockWidgetReceiver: GlanceAppWidgetReceiver() {
    private val workerLauncher = WorkerLauncher()

    override val glanceAppWidget: GlanceAppWidget
        get() = ClockWidget()

    override fun onReceive(context: Context, intent: Intent) {
        handleWidgetUpdate(context, intent)
        super.onReceive(context, intent)
    }

    private fun handleWidgetUpdate(context: Context, intent: Intent) {
        try {
            workerLauncher.launchWorker<UpdateWidgetWorker>(
                context.applicationContext,
                intent)
        } catch (e: Exception) {
            Log.e("ClockWidgetReceiver", "Error launching worker: ${e.message}")
        }
    }
}
