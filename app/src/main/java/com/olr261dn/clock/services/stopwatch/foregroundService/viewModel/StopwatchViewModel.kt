package com.olr261dn.clock.services.stopwatch.foregroundService.viewModel

import android.app.Application
import android.util.Log
import com.olr261dn.clock.services.common.foregroundService.BaseService
import com.olr261dn.clock.services.common.foregroundService.viewModel.BaseServiceBinderViewModel
import com.olr261dn.clock.services.stopwatch.foregroundService.StopwatchService
import com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchServiceController.StopwatchServiceController
import com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchState.StopwatchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class StopwatchViewModel @Inject constructor
    (application: Application): BaseServiceBinderViewModel(
        application, StopwatchService::class.java)
{

    private val _stopwatch = MutableStateFlow(StopwatchState())
    val stopwatch: StateFlow<StopwatchState> get() = _stopwatch

    override suspend fun assignData(service: BaseService) {
        (service as StopwatchService).stopwatchServiceController.stopwatch.collect {
            serviceData -> _stopwatch.value = serviceData
        }
    }

    private fun <T> withService(action: (StopwatchServiceController) -> T) {
        (getBinderService() as StopwatchService?)
            ?.stopwatchServiceController?.let(action)
            ?: Log.e("StopwatchService", "Service unavailable")
    }

    fun startTimer(){
        (getBinderService() as StopwatchService?)?.stopwatchServiceController?.startTimer()
        withService {
            startForeground()
            it.startTimer()
        }
    }

    fun stopTimer(){
        withService { it.stopTimer() }
    }

    fun resetTimer(){
        withService { it.resetTimer() }
    }

    fun getLap(){
        withService { it.getLap() }
    }
}