package com.olr261dn.clock.screens.alarm.utils

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class SnackBar(
    val coroutineScope: CoroutineScope,
    val snackBarHostState: SnackbarHostState) {

    fun showSnackBar(time: LocalDateTime) {
        coroutineScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) {
                snackBarHostState.currentSnackbarData?.dismiss()
                snackBarHostState.showSnackbar(AlarmOffset(time).getAlarmOffset())
            }
        }
    }
}


