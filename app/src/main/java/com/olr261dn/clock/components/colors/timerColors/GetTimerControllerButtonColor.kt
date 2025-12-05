package com.olr261dn.clock.components.colors.timerColors

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.colors.common.getOnSecondaryContainerColor
import com.olr261dn.clock.components.colors.common.getSecondaryContainerColor

@Composable
fun getTimerControllerButtonColor(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = getArcColor(),
        contentColor = getOnBackgroundColor(),
        disabledContentColor = getOnSecondaryContainerColor(),
        disabledContainerColor = getSecondaryContainerColor().copy(0.5f)
    )
}




