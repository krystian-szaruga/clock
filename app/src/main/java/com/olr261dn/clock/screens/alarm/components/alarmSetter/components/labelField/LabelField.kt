package com.olr261dn.clock.screens.alarm.components.alarmSetter.components.labelField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.ImeAction
import com.olr261dn.clock.components.textField.CreateLabelField

@Composable
fun LabelField(alarmLabel: MutableState<String>) {

    CreateLabelField(
        alarmLabel.value,
        labelText = "Alarm Label",
        keyboardAction = ImeAction.Done
    ) {
        alarmLabel.value = it
    }

}





