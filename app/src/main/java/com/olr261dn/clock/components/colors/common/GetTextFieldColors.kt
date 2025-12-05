package com.olr261dn.clock.components.colors.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
        focusedTextColor = MaterialTheme.colorScheme.onBackground,
        focusedContainerColor = MaterialTheme.colorScheme.background
    )
}

@Composable
fun getLabelTextColor(): Color {
    return MaterialTheme.colorScheme.onBackground
}