package com.olr261dn.clock.services.timer.foregroundService.viewModel

import android.app.Application
import android.util.Log
import com.olr261dn.clock.services.common.foregroundService.BaseService
import com.olr261dn.clock.services.common.foregroundService.viewModel.BaseServiceBinderViewModel
import com.olr261dn.clock.services.timer.alarm.TimerEndAlarmScheduler
import com.olr261dn.clock.services.timer.foregroundService.TimerService
import com.olr261dn.clock.services.timer.foregroundService.timerServiceController.TimerServiceController
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    application: Application): BaseServiceBinderViewModel(
        application, TimerService::class.java)
{
    val timerEndAlarm = TimerEndAlarmScheduler(
        application.applicationContext)
    private val _timers = MutableStateFlow<List<TimerState>>(emptyList())
    val timers: StateFlow<List<TimerState>> get() = _timers

    override suspend fun assignData(service: BaseService) {
        (service as TimerService).timerServiceController.timers.collect {
            serviceData -> _timers.value = serviceData.toList()
        }
    }

    private fun <T> withService(action: (TimerServiceController) -> T) {
        (getBinderService() as TimerService?)
            ?.timerServiceController?.let(action)
            ?: Log.e("TimerService", "Service unavailable")
    }

    fun initTimeLeft(
        time: Long, id: Int, updateFlag: Boolean = true, updateFromStop: Boolean = false){
        timerEndAlarm.cancelAlarm(id)
        withService { it.initializeData(time, id, updateFlag, updateFromStop = updateFromStop) }
    }


    fun removeTimer(id: Int) {
        timerEndAlarm.cancelAlarm(id)
        withService { it.removeTimer(id) }
    }

    fun modifyTimeOnGo(newTime: Long, id: Int) {
        timerEndAlarm.cancelAlarm(id)
        timerEndAlarm.setAlarm(
            Instant.now()
                .plusMillis(newTime)
                .toEpochMilli(),
            id
        )
        withService { it.modifyTimeOnGo(newTime, id) }
    }

    fun startTimer(triggerTime: Long, id: Int) {
        timerEndAlarm.setAlarm(triggerTime, id)
        withService {
            startForeground()
            it.startTimer(id)
        }
    }

    fun resetTimer(id: Int) {
        timerEndAlarm.cancelAlarm(id)
        withService { it.removeTimer(id) }
    }

    fun stopTimer(id: Int){
        timerEndAlarm.cancelAlarm(id)
        withService { it.stopTimer(id) }
    }
}