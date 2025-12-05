package com.olr261dn.clock.screens.settings.settingsItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.colors.common.getThirdBackgroundColor
import com.olr261dn.clock.screens.settings.utils.GetHorizontalDivider
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingRowWithDropdown(
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Surface (
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        color = getBackgroundColor(),
        contentColor = getOnBackgroundColor()
    ) {
        Column(
            modifier = Modifier.padding(start = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = label,
                fontSize = 25.nonScaledSp
            )
            Spacer(Modifier.height(5.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = true },
            ) {
                DropDownText(
                    modifier = Modifier.menuAnchor(
                        MenuAnchorType.PrimaryEditable, enabled = true
                    ),
                    selectedOption = selectedOption,
                    label = label
                )
                ExposedDropdownMenu(
                    containerColor = getThirdBackgroundColor(),
                    expanded = expanded,
                    onDismissRequest = { expanded = !expanded }
                ) {
                    ExpandedContent(options=options) {
                        onOptionSelected(it)
                        expanded = false
                    }
                }
            }
        }
    }
    GetHorizontalDivider()
}

@Composable
private fun DropDownText(
    modifier: Modifier,
    selectedOption: String,
    label: String
){
    TextField(
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = getThirdBackgroundColor(),
            unfocusedTextColor = getOnBackgroundColor(),
            focusedContainerColor = getThirdBackgroundColor(),
            focusedTextColor = getOnBackgroundColor(),
        ),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        value = selectedOption,
        onValueChange = {},
        label = {
            Text(
                fontSize = 12.nonScaledSp,
                text = "Select $label")
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop-down menu")
        },
        readOnly = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun ExpandedContent(
    options: List<String>,
    onClick: (String) -> Unit){
    options.forEach { option ->
        DropdownMenuItem(
            colors = MenuDefaults.itemColors(
                textColor = getOnBackgroundColor()
            ),
            onClick = {
                onClick(option)
            },
            text = {
                Text(option)
            }
        )
    }
}
