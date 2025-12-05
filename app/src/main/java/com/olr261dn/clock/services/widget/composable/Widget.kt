package com.olr261dn.clock.services.widget.composable


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import com.olr261dn.clock.components.colors.widget.getWidgetColor
import com.olr261dn.clock.services.widget.composable.components.ActualDateBox
import com.olr261dn.clock.services.widget.composable.components.ActualTimeBox
import com.olr261dn.clock.services.widget.composable.components.UpcomingAlarm
import kotlinx.coroutines.flow.StateFlow

@Composable
fun Widget(
    timePatternFlow: StateFlow<String>,
    nextAlarmFlow: StateFlow<String?>,
    onClick: () -> Unit)
{
    val nextAlarm by nextAlarmFlow.collectAsState()
    val timePattern by timePatternFlow.collectAsState()
    val packageName = LocalContext.current.packageName

    Box(modifier = GlanceModifier.clickable { onClick() }
    ) {
        Column(
            modifier = GlanceModifier
                .padding(
                    start = 5.dp,
                    end = 5.dp,
                    top = if (nextAlarm == null) 20.dp else 10.dp)
                .cornerRadius(20.dp)
                .fillMaxSize()
                .background(getWidgetColor()),
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActualTimeBox(timePattern, packageName)
            Spacer(GlanceModifier.height(15.dp))
            ActualDateBox(packageName)
            Column (
                modifier = GlanceModifier.fillMaxSize()
                    .padding(bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.Bottom
            ){
                nextAlarm?.let {
                    UpcomingAlarm(it, packageName)
                }
            }
        }
    }
}

