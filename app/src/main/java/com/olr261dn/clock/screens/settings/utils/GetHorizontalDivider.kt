package com.olr261dn.clock.screens.settings.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor

@Composable
fun GetHorizontalDivider(){
    HorizontalDivider(
        modifier = Modifier
            .padding(
                top = 15.dp, bottom = 15.dp
            )
            .fillMaxWidth(),
        color = getSecondBackgroundColor(),
        thickness = 5.dp)
}
