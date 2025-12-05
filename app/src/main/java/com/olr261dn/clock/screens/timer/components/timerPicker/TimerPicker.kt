package com.olr261dn.clock.screens.timer.components.timerPicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.screens.timer.components.timerPicker.utils.TimerPresetsRow
import com.olr261dn.clock.screens.timer.components.timerPicker.utils.WheelPicker
import com.olr261dn.clock.screens.timer.components.utils.TimerDuration
import com.olr261dn.clock.viewModels.UserSettingsViewModel

@Composable
fun TimerPicker(
    duration: TimerDuration,
    wheelPickerViewModel: UserSettingsViewModel,
    onCancel: () -> Unit,
    onTimeSelected: () -> Unit) {
    var showScrollableChooser by rememberSaveable {
        mutableStateOf(true)
    }
    var selectedPreset by rememberSaveable {
        mutableStateOf<TimerDuration?>(null) }

    SurfaceWithColumn(
        modifier = Modifier.fillMaxSize(),
        customBackgroundColor = getSecondBackgroundColor()
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select Time",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.headlineLarge)

            TimerPresetsRow(selectedPreset = selectedPreset) {
                showScrollableChooser = false
                selectedPreset = it
                wheelPickerViewModel.updateDurationValue(it)
            }

            IconButton(
                onClick = {
                    selectedPreset = null
                    showScrollableChooser =
                        !showScrollableChooser }) {
                Icon(
                    imageVector = if (showScrollableChooser)
                        Icons.Filled.KeyboardArrowUp else
                            Icons.Default.KeyboardArrowDown,
                    contentDescription = "Show/Hide Scrollable Picker",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            if (showScrollableChooser) {
                WheelPickerRow(duration, wheelPickerViewModel)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { onCancel() }) {
                    Text("Cancel")
                }
                Spacer(Modifier.width(10.dp))
                Button(onClick = { onTimeSelected() }) {
                    Text("Set Time")
                }
            }
        }
    }
}


@Composable
private fun WheelPickerRow(
    duration: TimerDuration,
    wheelPickerViewModel: UserSettingsViewModel)
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WheelPicker(
            value = duration.hourIndex,
            range = 0..23,
            label = "Hrs"
        ) {
            wheelPickerViewModel.updateHourValue(it)
        }
        WheelPicker(
            value = duration.minuteIndex,
            range = 0..59,
            label = "Min"
        ) {
            wheelPickerViewModel.updateMinuteValue(it)
        }

        WheelPicker(
            value = duration.secondIndex,
            range = 0..59,
            label = "Sec"
        ) {
            wheelPickerViewModel.updateSecondValue(it)
        }
    }
}
