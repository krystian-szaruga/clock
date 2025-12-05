package com.olr261dn.clock.screens.alarm.components.alarmSetter.components.dayPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.alarmScreen.getCheckedBoxColor
import com.olr261dn.clock.components.colors.alarmScreen.getDefaultBoxColor
import com.olr261dn.clock.components.colors.alarmScreen.getDropdownBackgroundColor
import com.olr261dn.clock.components.colors.alarmScreen.getDropdownTextColor
import com.olr261dn.clock.components.colors.alarmScreen.getOnCheckedBoxColor
import com.olr261dn.clock.components.colors.alarmScreen.getOnDefaultBoxColor
import com.olr261dn.clock.components.textField.CreateLabelField
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import java.time.DayOfWeek

@Composable
fun DayPicker(
    defaultOption: String = "Custom",
    selectedDays: MutableState<Set<DayOfWeek>>) {

    var selectedOption by remember { mutableStateOf(defaultOption) }
    val oneTime = setOf<DayOfWeek>()
    val allDays = DayOfWeek.entries.toSet()
    val workDays = setOf(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
    val weekendDays = setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)

    Column {
        PopupSelection(selectedOption = selectedOption) {
            selectedOption = it
        }
        CustomDayPicker(selectedDays.value) { day ->
            selectedDays.value = if (selectedDays.value.contains(day)) {
                selectedDays.value - day
            } else {
                selectedDays.value + day
            }
            selectedOption = "Custom"

        }
        when (selectedOption) {
            "One Time" -> selectedDays.value = oneTime
            "Every Day" -> selectedDays.value = allDays
            "Work Day" -> selectedDays.value =  workDays
            "Weekend" -> selectedDays.value = weekendDays
        }
    }
}


@Composable
fun CustomDayPicker(
    selectedDays: Set<DayOfWeek>,
    onCustomDaySelected: (DayOfWeek) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        items(DayOfWeek.entries){ day ->
            val isSelected = selectedDays.contains(day)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(1.dp))
                    .background(if (isSelected) getCheckedBoxColor() else getDefaultBoxColor())
                    .clickable { onCustomDaySelected(day) }
            ) {
                Text(
                    text = day.name.take(3),
                    color = if (isSelected) getOnCheckedBoxColor() else getOnDefaultBoxColor(),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.nonScaledSp
                )
            }
        }
    }
}

@Composable
fun PopupSelection(
    selectedOption: String,
    onOptionSelect: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val options = listOf("One Time", "Every Day", "Work Day", "Weekend")

    Column {
        CreateLabelField(
            value = selectedOption,
            readOnly = true,
            trailingIcon = @Composable {
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Expand Drop Down Menu"
                    )
                }
            },
            labelText = "Repeating Frequency"
        )
        DropdownMenu(
            modifier = Modifier
                .background(getDropdownBackgroundColor()),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelect(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            option,
                            color = getDropdownTextColor()) }
                )
            }
        }
    }
}

