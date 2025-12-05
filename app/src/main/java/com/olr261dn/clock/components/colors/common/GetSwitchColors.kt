package com.olr261dn.clock.components.colors.common

import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable

@Composable
fun getSwitchColors(): SwitchColors {
    return SwitchDefaults.colors().copy(
        checkedTrackColor = getThirdBackgroundColor(),
        checkedThumbColor = getOnBackgroundColor()
    )
}