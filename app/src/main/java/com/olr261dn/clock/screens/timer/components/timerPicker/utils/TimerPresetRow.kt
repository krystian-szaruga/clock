package com.olr261dn.clock.screens.timer.components.timerPicker.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.colors.common.getOnPrimaryContainerColor
import com.olr261dn.clock.components.colors.common.getPrimaryContainerColor
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor
import com.olr261dn.clock.screens.timer.components.utils.TimerDuration
import com.olr261dn.clock.utils.fontSize.nonScaledSp

object Presets {
    val timerPreset = listOf(
        TimerDuration(0, 0, 30),
        TimerDuration(0, 1, 0),
        TimerDuration(0, 2, 0),
        TimerDuration(0, 3, 0),
        TimerDuration(0, 5, 0),
        TimerDuration(0, 10, 0),
        TimerDuration(0, 15, 0),
        TimerDuration(0, 20, 0),
        TimerDuration(0, 30, 0),
        TimerDuration(0, 35, 0),
        TimerDuration(0, 40, 0),
        TimerDuration(0, 45, 0),
        TimerDuration(0, 50, 0),
        TimerDuration(0, 55, 0),
        TimerDuration(1, 0, 0),
        TimerDuration(1, 30, 0),
        TimerDuration(2, 0, 0),
        TimerDuration(3, 0, 0),
    )
}


@Composable
fun TimerPresetsRow(
    selectedPreset: TimerDuration?,
    onPresetSelected: (TimerDuration) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(Presets.timerPreset) { preset ->
            val backgroundColor = when (selectedPreset == preset) {
                true -> getPrimaryContainerColor()
                else -> getSecondBackgroundColor()
            }
            val onBackgroundColor = when (selectedPreset == preset) {
                true -> getOnPrimaryContainerColor()
                else -> getOnBackgroundColor()
            }
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(backgroundColor, RoundedCornerShape(16.dp))
                    .clickable {
                        onPresetSelected(preset)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = preset.toString(),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 13.nonScaledSp,
                    color = onBackgroundColor
                )
            }
        }
    }
}
