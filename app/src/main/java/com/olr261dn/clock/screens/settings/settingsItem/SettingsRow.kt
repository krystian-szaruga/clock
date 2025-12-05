package com.olr261dn.clock.screens.settings.settingsItem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.olr261dn.clock.components.colors.common.getSwitchColors
import com.olr261dn.clock.screens.settings.utils.GetHorizontalDivider
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@Composable
fun SettingRow(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 20.nonScaledSp,
            modifier = Modifier.weight(1f)
        )
        Switch(
            colors = getSwitchColors(),
            checked = isChecked,
            onCheckedChange = onCheckedChange,
        )
    }
    GetHorizontalDivider()
}
