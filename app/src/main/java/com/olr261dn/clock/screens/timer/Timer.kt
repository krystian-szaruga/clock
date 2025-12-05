package com.olr261dn.clock.screens.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.olr261dn.clock.components.commonComposable.EmptyScreenMsg
import com.olr261dn.clock.components.scaffoldBar.ScaffoldBar
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.screens.timer.components.timerPicker.TimerPicker
import com.olr261dn.clock.screens.timer.components.timerProgress.TimerProgress
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState
import com.olr261dn.clock.services.timer.foregroundService.viewModel.TimerViewModel
import com.olr261dn.clock.viewModels.UserSettingsViewModel

@Composable
fun Timer(
    wheelPickerViewModel: UserSettingsViewModel = hiltViewModel(),
    timerViewModel: TimerViewModel = hiltViewModel(),
    navigateToScreen: NavigateToScreen)
{
    val timers by timerViewModel.timers.collectAsState()
    val duration by wheelPickerViewModel.duration.collectAsState()
    var showTimePicker = remember { mutableStateOf(false) }
    val totalTime by remember(duration) {
        derivedStateOf {
            duration.calculateTotalMillis()
        }
    }
    val listState = rememberLazyListState()

    LaunchedEffect(timers.size) {
        if (timers.isNotEmpty()) {
            listState.animateScrollToItem(timers.size - 1)
        }
    }
    val fabContentOnTapIfVisible = {
        val newId = if (timers.isNotEmpty())
            timers.maxByOrNull { it.id }!!.id + 1 else 0
        timerViewModel.initTimeLeft(
            totalTime, newId
        )
    }
    ScaffoldBar(
        initialScrollIndex = 3,
        title = "Timer",
        fabContentOnTap = if (!showTimePicker.value) fabContentOnTapIfVisible else null,
        navigateToScreen = navigateToScreen) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(it)) {
            TimerScreen(
                showTimePicker,
                wheelPickerViewModel,
                timerViewModel,
                timers,
                listState)
        }
    }
}


@Composable
fun TimerScreen(
    showTimePicker: MutableState<Boolean>,
    wheelPickerViewModel: UserSettingsViewModel,
    timerViewModel: TimerViewModel,
    timers: List<TimerState>,
    listState: LazyListState
) {
    val duration by wheelPickerViewModel.duration.collectAsState()
    var stateId by remember { mutableIntStateOf(0) }
    val totalTime by remember(duration) {
        derivedStateOf {
            duration.calculateTotalMillis()
        }
    }
    if (showTimePicker.value) {
        TimerPicker(
            duration = duration,
            wheelPickerViewModel = wheelPickerViewModel,
            onCancel = {
                showTimePicker.value = false
            },
            onTimeSelected = {
                timerViewModel.resetTimer(stateId)
                wheelPickerViewModel.updateTimerValues()
                timerViewModel.initTimeLeft(totalTime, stateId)
                showTimePicker.value = false
            }
        )
    }
    if (timers.isEmpty()) EmptyScreenMsg("Press the '+' button to set a new timer.")
    TimerProgress(
        listState = listState,
        timerViewModel = timerViewModel,
        timers = timers){
        showTimePicker.value = true
        stateId = it
    }
}




