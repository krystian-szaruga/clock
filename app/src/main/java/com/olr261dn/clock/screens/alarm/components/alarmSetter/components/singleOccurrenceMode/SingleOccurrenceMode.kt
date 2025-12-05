package com.olr261dn.clock.screens.alarm.components.alarmSetter.components.singleOccurrenceMode

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor

@Composable
fun SingleOccurrenceMode(
    isSingleOccurrence: Boolean,
    isEnabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Checkbox(
            enabled = isEnabled,
            checked = isSingleOccurrence,
            onCheckedChange = { onCheckedChange(it) }
        )
        Text(
            color = if (isEnabled) getOnBackgroundColor() else
                getOnBackgroundColor().copy(0.1f),
            text = "Single Occurrence Mode")
    }
}