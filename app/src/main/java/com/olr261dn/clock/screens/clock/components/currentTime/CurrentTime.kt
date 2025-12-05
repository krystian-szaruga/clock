package com.olr261dn.clock.screens.clock.components.currentTime

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@Composable
fun CurrentTime(currentTime: String, currentDate: String) {
    SurfaceWithColumn {
        TimeDisplay(currentTime)
        DateDisplay(currentDate)
    }
}

@Composable
fun TimeDisplay(currentTime: String) {
    Text(
        text = currentTime,
        maxLines = 1,
        color = getOnBackgroundColor(),
        fontSize = 50.nonScaledSp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DateDisplay(currentDate: String) {
    Text(
        text = currentDate,
        color = getOnBackgroundColor(),
        fontSize = 18.nonScaledSp,
        textAlign = TextAlign.Center
    )
}
