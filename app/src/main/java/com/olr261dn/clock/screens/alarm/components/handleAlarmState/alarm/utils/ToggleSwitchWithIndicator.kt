package com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getSwitchColors


@Composable
fun ToggleSwitchWithIndicator(
    isEnabled: Boolean,
    onToggleChange: (Boolean) -> Unit) {

    Switch(
        colors = getSwitchColors(),
        modifier = Modifier
            .size(60.dp),
        checked = isEnabled,
        onCheckedChange = { onToggleChange(it) }
    )
}
