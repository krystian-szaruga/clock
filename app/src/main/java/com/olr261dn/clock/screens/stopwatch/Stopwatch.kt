package com.olr261dn.clock.screens.stopwatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.olr261dn.clock.R
import com.olr261dn.clock.components.buttons.CreateControlButton
import com.olr261dn.clock.components.buttons.TimeControlButtons
import com.olr261dn.clock.components.scaffoldBar.ScaffoldBar
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.screens.stopwatch.displayStopwatchState.DisplayStopwatchState
import com.olr261dn.clock.services.stopwatch.foregroundService.viewModel.StopwatchViewModel

@Composable
fun Stopwatch(
    navigateToScreen: NavigateToScreen,
    stopwatchViewModel: StopwatchViewModel = hiltViewModel()) {
    val stopwatch by stopwatchViewModel.stopwatch.collectAsState()
    ScaffoldBar(
        title = "Stopwatch",
        initialScrollIndex = 4,
        navigateToScreen = navigateToScreen
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CreateControlButton(
                enabled = stopwatch.isTimerRunning,
                icon = R.drawable.ic_timer,
                description = "Take a lap"
            ) { stopwatchViewModel.getLap() }
            Spacer(Modifier.height(10.dp))
            DisplayStopwatchState(stopwatch)
            Spacer(Modifier.height(20.dp))
            TimeControlButtons(
                isTimerRunning = stopwatch.isTimerRunning,
                isTimerStopped = stopwatch.isTimerStopped,
                onStart = { stopwatchViewModel.startTimer() },
                onStop = { stopwatchViewModel.stopTimer() },
                onReset = { stopwatchViewModel.resetTimer() }
            )
        }
    }
}
