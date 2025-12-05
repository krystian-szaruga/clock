package com.olr261dn.clock.screens.alarm.components.alarmSetter.components.timePicker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.olr261dn.clock.R
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getGradientsColors
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTimePicker(
    timePickerState: TimePickerState) {
    val showTimePicker = rememberSaveable { mutableStateOf(false) }


    val (formatedHour, formatedMinutes, amPm) = formatTime(
        timePickerState.is24hour, timePickerState.hour, timePickerState.minute)


    val formattedTime = "$formatedHour:$formatedMinutes $amPm"

    TextButton(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        border = BorderStroke(3.dp, Brush.sweepGradient(getGradientsColors())),
        colors = ButtonDefaults.buttonColors(
            containerColor = getBackgroundColor(),
            contentColor = getOnBackgroundColor()
        ),
        onClick = { showTimePicker.value = !showTimePicker.value })
    {
        Text(
            fontSize = 44.nonScaledSp,
            fontWeight = FontWeight.ExtraBold,
            text = formattedTime
        )
    }
    if (showTimePicker.value) {
        TimePickerDialog(
            onDismissRequest = { showTimePicker.value = false },
            confirmButton = {
                TextButton(
                    onClick = { showTimePicker.value = false })
                { Text(
                    fontSize = 20.nonScaledSp,
                    text = "OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = { showTimePicker.value = false })
                { Text(
                    fontSize = 20.nonScaledSp,
                    text = "Cancel") }
            },
            timeInput = {
                TimeInput(state = timePickerState)
            }
        ) {
            TimePicker(
                state = timePickerState,
                layoutType = TimePickerDefaults.layoutType()
            )
        }

    }
}

fun formatTime(is24hour: Boolean, hour: Int, minutes: Int): Triple<String, String, String> {
    val formattedMinutes = if (minutes < 10) "0$minutes" else minutes.toString()
    return if (is24hour) {
        Triple(hour.toString(), formattedMinutes, "")
    } else {
        val formattedHour = when {
            hour == 0 -> "12"
            hour > 12 -> (hour - 12).toString()
            else -> hour.toString()
        }.padStart(2, '0')
        val period = if (hour < 12) "AM" else "PM"
        Triple(formattedHour, formattedMinutes, period)
    }
}





@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    timeInput: @Composable () -> Unit,
    timePicker: @Composable () -> Unit,
) {
    val keyboardInput = rememberSaveable {
        mutableStateOf(false)
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false))
    {
        Surface(
            tonalElevation = 6.dp,
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = getBackgroundColor()
                ),
            color = getBackgroundColor()
        ) {
            LazyColumn (
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 50.nonScaledSp,
                        text = title,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                item { if (!keyboardInput.value) timePicker() else timeInput() }
                item {
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        IconButton(onClick = { keyboardInput.value = !keyboardInput.value }) {
                            Icon(
                                painter = painterResource(
                                    id = if (!keyboardInput.value)
                                        R.drawable.baseline_keyboard_24 else
                                        R.drawable.baseline_watch_24
                                ),
                                contentDescription = "Set Time Using Keyboard"
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        dismissButton()
                        confirmButton()
                    }
                }
            }
        }
    }
}
