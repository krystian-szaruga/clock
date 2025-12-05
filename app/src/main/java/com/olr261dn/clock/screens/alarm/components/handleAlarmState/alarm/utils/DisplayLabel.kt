package com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@Composable
fun DisplayLabel(
    size: TextUnit = 20.nonScaledSp,
    label: String,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Surface(
        color = Color.Transparent,
        contentColor = getOnBackgroundColor(),
        modifier = Modifier
            .padding(1.dp)
    ) {
        Text(
            fontSize = size,
            textAlign = TextAlign.Center,
            text = label,
            style = style,
            fontWeight = fontWeight
        )
    }
}