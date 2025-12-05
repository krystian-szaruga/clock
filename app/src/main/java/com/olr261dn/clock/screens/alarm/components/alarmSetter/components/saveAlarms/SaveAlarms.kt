package com.olr261dn.clock.screens.alarm.components.alarmSetter.components.saveAlarms

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olr261dn.clock.components.colors.common.getButtonColors

@Composable
fun SaveAlarm(onSave: () -> Unit){
    Button(
        modifier = Modifier.padding(top = 10.dp),
        colors = getButtonColors(),
        onClick = { onSave() }) {
        Text(
            text = "Save Alarm",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.headlineMedium)
    }
}
