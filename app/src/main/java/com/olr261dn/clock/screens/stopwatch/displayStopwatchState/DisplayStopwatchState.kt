package com.olr261dn.clock.screens.stopwatch.displayStopwatchState

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchState.StopwatchState

@Composable
fun DisplayStopwatchState(stopwatch: StopwatchState) {
    SurfaceWithColumn(
        modifier = Modifier
            .fillMaxWidth(0.97f)
            .fillMaxHeight(0.6f)) {
        Text(
            textAlign = TextAlign.Center,
            text = stopwatch.timeFormatted,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 42.sp
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            thickness = 3.dp,
            color = getSecondBackgroundColor()
        )
        Text(
            text = stopwatch.timeFormatted,
            textAlign = TextAlign.Center)
        LazyColumn {
            itemsIndexed(stopwatch.laps.reversed()) { idx, leap ->
                val realIndex = stopwatch.laps.size - idx
                Text(
                    text = "Lap $realIndex: $leap",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}