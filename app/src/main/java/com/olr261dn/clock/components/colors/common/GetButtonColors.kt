package com.olr261dn.clock.components.colors.common

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun getButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = getThirdBackgroundColor(),
        contentColor = getOnBackgroundColor(),
        disabledContentColor = MaterialTheme.colorScheme.secondary.copy(0.4f),
        disabledContainerColor = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
fun getIconButtonColors(): IconButtonColors {
    return IconButtonDefaults.iconButtonColors(
        containerColor = getThirdBackgroundColor(),
        contentColor = getOnBackgroundColor(),
        disabledContentColor = MaterialTheme.colorScheme.primary.copy(0.7f),
        disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(0.2f)
    )
}