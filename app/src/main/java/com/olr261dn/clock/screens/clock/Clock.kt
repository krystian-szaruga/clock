package com.olr261dn.clock.screens.clock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olr261dn.clock.components.buttons.CreateButton
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getButtonColors
import com.olr261dn.clock.components.scaffoldBar.ScaffoldBar
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.screens.clock.components.analogClock.AnalogClock
import com.olr261dn.clock.screens.clock.components.currentTime.CurrentTime
import com.olr261dn.clock.screens.clock.components.gradientDivider.GradientDivider
import com.olr261dn.clock.screens.clock.viewModel.ActualTimeViewModel
import com.olr261dn.clock.viewModels.GlobalSettingsViewModel
import com.olr261dn.clock.viewModels.UserSettingsViewModel


@Composable
fun Clock(
    navigateToScreen: NavigateToScreen,
    globalSettingsViewModel: GlobalSettingsViewModel = hiltViewModel(),
    analogClockViewModel: UserSettingsViewModel = hiltViewModel(),
    actualTimeViewModel: ActualTimeViewModel = viewModel()
) {
    val currentTimeMills = actualTimeViewModel.currTimeMills.collectAsState()
    val showAnalogClock = analogClockViewModel.showAnalogClock.collectAsState()
    val currentTime = actualTimeViewModel.currentTime.collectAsState()
    val currentDate = actualTimeViewModel.currentDate.collectAsState()
    val timeFormat = globalSettingsViewModel.timeFormat.collectAsState()
    actualTimeViewModel.startTime(timeFormat.value)

    LaunchedEffect(timeFormat) {
        globalSettingsViewModel.initialize()
    }

    ScaffoldBar(
        title = "Clock",
        navigateToScreen = navigateToScreen,
        initialScrollIndex = 0) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = getBackgroundColor()
        ) {
            LazyColumn (
                modifier = Modifier.fillMaxSize().padding(top = 15.dp, bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item { CurrentTime(
                    currentTime = currentTime.value, currentDate = currentDate.value)
                }
                item { GradientDivider() }
                item { AnalogClock(currentTimeMills, showAnalogClock.value) {
                    ShowAnalogClockButton(showAnalogClock.value){
                        analogClockViewModel.updateShowAnalogClock(!showAnalogClock.value)
                    }
                } }
            }
        }
    }
}

@Composable
private fun ShowAnalogClockButton(
    showAnalogClock: Boolean,
    onClick: () -> Unit)
{
    CreateButton(
        text = if (!showAnalogClock) "Show AnalogClock"
        else "Hide Analog Clock",
        buttonColors = getButtonColors(),
        style = MaterialTheme.typography.bodySmall) { onClick.invoke() }
}
