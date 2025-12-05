package com.olr261dn.clock.screens.timer.components.timerProgress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.screens.timer.components.timerProgress.timerItem.TimerItem
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState
import com.olr261dn.clock.services.timer.foregroundService.viewModel.TimerViewModel


@Composable
fun TimerProgress(
    listState: LazyListState,
    timerViewModel: TimerViewModel,
    timers: List<TimerState>,
    onClick: (Int) -> Unit) {

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(top=10.dp)
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(timers.sortedBy { it.id }) { timer ->
            val additionalInfo = when {
                timer.isTimerStopped -> "Stopped"
                timer.isTimerRunning -> "Running"
                else -> "Not Running"
            }
            SurfaceWithColumn(
                padding = 2.dp) {
                SurfaceWithColumn(
                    modifier = Modifier.fillMaxWidth(0.95f)) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Timer: ${timer.id + 1} ($additionalInfo)",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                TimerItem(
                    id = timer.id,
                    timer = timer,
                    timerViewModel = timerViewModel) { onClick.invoke(it) }
            }
            Spacer(Modifier.height(80.dp))
        }
    }
}
