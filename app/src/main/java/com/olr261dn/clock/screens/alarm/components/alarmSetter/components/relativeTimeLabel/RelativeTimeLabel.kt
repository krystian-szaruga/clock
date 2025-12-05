package com.olr261dn.clock.screens.alarm.components.alarmSetter.components.relativeTimeLabel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor

@Composable
fun RelativeTimeLabel(label: String) {
    Surface(
        contentColor = getOnBackgroundColor(),
        shape = RoundedCornerShape(5.dp),
        color = getBackgroundColor(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
    {
        Row(
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Calendar Icon",
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                maxLines = 1,
                text = label,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}