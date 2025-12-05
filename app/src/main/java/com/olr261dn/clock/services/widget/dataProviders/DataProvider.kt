package com.olr261dn.clock.services.widget.dataProviders

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataProvider {

    private val _nextAlarmFlow = MutableStateFlow<String?>(null)
    val nextAlarmFlow: StateFlow<String?> = _nextAlarmFlow
    private val _timePattern = MutableStateFlow<String>("hh:mm a")
    val timePattern: StateFlow<String> = _timePattern

    companion object {
        val instance: DataProvider by lazy {
            DataProvider() }
    }

    fun updateWidgetData(nextAlarm: String?, newPattern: String){
        updateNextAlarm(nextAlarm)
        updateTimePattern(newPattern)
    }

    private fun updateNextAlarm(nextAlarm: String?) {
        _nextAlarmFlow.value = nextAlarm
    }

    private fun updateTimePattern(newPattern: String) {
        _timePattern.value = newPattern
    }
}