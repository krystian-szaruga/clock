package com.olr261dn.clock.screens.worldClocks.timeDisplayer.components

import androidx.compose.runtime.Composable
import com.olr261dn.clock.components.buttons.CreateButton
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@Composable
fun DeleteAllClocks(
    onPress: () -> Unit) {
    CreateButton(
        size = 20.nonScaledSp,
        text = "Remove All Clocks") {
        onPress.invoke()
    }
}