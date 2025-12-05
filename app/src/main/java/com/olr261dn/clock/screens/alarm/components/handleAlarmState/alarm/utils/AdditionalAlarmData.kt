package com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import java.time.format.DateTimeFormatter

@Composable
fun AdditionalAlarmData(alarm: AlarmItem) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        DisplayLabel(
            size = 13.nonScaledSp,
            label = if (alarm.isEnabled) alarm.time.format(
                DateTimeFormatter.ofPattern(
                    "E dd MMM"
                )
            ) else "")
        DisplaySelectedDays(days = alarm.repeatDays)
    }
    Surface (
        modifier = Modifier.fillMaxWidth()
    ){
        if (alarm.isSingleOccurrence) Text(
            textAlign = TextAlign.Center,
            fontSize = 9.nonScaledSp,
            text = "(Once on set days)"
        )
    }
}






