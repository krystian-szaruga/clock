package com.olr261dn.clock.services.widget

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import com.olr261dn.clock.MainActivity
import com.olr261dn.clock.services.widget.composable.Widget
import com.olr261dn.clock.services.widget.dataProviders.DataProvider


class ClockWidget: GlanceAppWidget() {
    private val dataProvider = DataProvider.instance

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                Widget(
                    timePatternFlow = dataProvider.timePattern,
                    nextAlarmFlow = dataProvider.nextAlarmFlow
                ){
                    getMainActivityIntent(context).send()
                }
            }
        }
    }

    private fun getMainActivityIntent(context: Context): PendingIntent {
        return PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        super.onDelete(context, glanceId)
    }

    suspend fun updateWidget(context: Context, nextAlarm: String?, newPattern: String) {
        dataProvider.updateWidgetData(nextAlarm, newPattern)
        updateAll(context)
    }

}
