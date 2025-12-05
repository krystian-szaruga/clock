package com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import com.olr261dn.clock.utils.timeConverters.localDateTime.TimeToStringWithPattern

@Composable
fun DisplaySnoozeAlarmInfo(
    timeFormat: String,
    snoozeAlarm: AlarmItem,
    onSnoozeCancel: (AlarmItem) -> Unit) {

    Spacer(Modifier.width(10.dp))
    DisplayLabel(
        size = 15.nonScaledSp,
        label = TimeToStringWithPattern(snoozeAlarm.time, timeFormat).convert(),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Normal
    )
    Icon(
        imageVector = Icons.Default.Done,
        tint = getOnBackgroundColor(),
        contentDescription = "Cancel",
        modifier = Modifier
            .size(12.dp)
            .clickable { onSnoozeCancel(snoozeAlarm) }
    )
}
