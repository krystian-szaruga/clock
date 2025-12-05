package com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import java.time.DayOfWeek

@Composable
fun DisplaySelectedDays(days: Set<DayOfWeek>) {
    val fontSize = 13.nonScaledSp
    LazyRow(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        item {
            when {
                days.isEmpty() -> {
                    DisplayLabel(
                        size = fontSize,
                        label = "One-time alarm",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                days == DayOfWeek.entries.toSet() -> {
                    DisplayLabel(
                        size = fontSize,
                        label = "Every Day",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                else -> {
                    days.forEachIndexed { index, day ->
                        val label = day.name.take(3).lowercase()
                            .replaceFirstChar { it.uppercase() }
                        DisplayLabel(
                            label = if (index == days.size - 1) label else "$label,",
                            style = MaterialTheme.typography.bodySmall,
                            size = fontSize
                        )
                    }
                }
            }
        }
    }
}
