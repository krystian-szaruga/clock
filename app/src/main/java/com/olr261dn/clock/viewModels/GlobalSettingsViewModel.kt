package com.olr261dn.clock.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.TimePatternResolver
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.timeFormatType.TimeFormatType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlobalSettingsViewModel @Inject constructor(
    val repository: GlobalSettingsRepository): ViewModel()
{
    private val _snoozeTime = MutableStateFlow("10 min")
    val snoozeTime = _snoozeTime.asStateFlow()
    private val _timeFormatType = MutableStateFlow(TimeFormatType.SYSTEM)
    val timeFormatType = _timeFormatType.asStateFlow()
    private val _timeFormat = MutableStateFlow(TimePatternResolver(
        _timeFormatType.value).getFormat())
    val timeFormat = _timeFormat.asStateFlow()
    private val _showAlarmInfo = MutableStateFlow(true)
    val showAlarmInfo = _showAlarmInfo.asStateFlow()
    private val _earlyAlarmReminder = MutableStateFlow(true)
    val earlyAlarmReminder = _earlyAlarmReminder.asStateFlow()
    private val _increaseVolumeGradually = MutableStateFlow(true)
    val increaseVolumeGradually = _increaseVolumeGradually.asStateFlow()
    private val _displaySecondsInWidget = MutableStateFlow(false)
    val displaySecondsInWidget = _displaySecondsInWidget.asStateFlow()

    init {
        initialize()
    }

    fun initialize() {
        viewModelScope.launch{
            val globalSettings = repository.getGlobalSettings()
            _snoozeTime.value = globalSettings.snoozeTime
            _timeFormatType.value = globalSettings.timeFormat
            _displaySecondsInWidget.value = globalSettings.displaySecondsInWidget
            _timeFormat.value = TimePatternResolver(_timeFormatType.value).getFormat()
            Log.d("TAG", "initialize: ${_timeFormat.value}")
            _showAlarmInfo.value = globalSettings.showAlarmInfo
            _earlyAlarmReminder.value = globalSettings.earlyAlarmReminder
            _increaseVolumeGradually.value = globalSettings.increaseVolumeGradually
        }
    }

    fun updateSnoozeTime(snoozeTime: String) {
        updateSetting(snoozeTime, repository::updateSnoozeTime, _snoozeTime)
    }

    fun updateTimeFormat(timeFormatType: String, displaySeconds: Boolean) {
        updateSetting(timeFormatType, repository::updateTimeFormat, _timeFormatType)
        _timeFormat.value = TimePatternResolver(timeFormatType, displaySeconds).getFormat()
    }

    fun updateShowAlarmInfo(showAlarmInfo: Boolean) {
        updateSetting(showAlarmInfo, repository::updateShowAlarmInfo, _showAlarmInfo)
    }

    fun updateEarlyAlarmReminder(earlyAlarmReminder: Boolean) {
        updateSetting(
            earlyAlarmReminder, repository::updateEarlyAlarmReminder, _earlyAlarmReminder)
    }

    fun updateIncreaseVolumeGradually(increaseVolFlag: Boolean) {
        updateSetting(
            increaseVolFlag, repository::updateIncreaseVolumeGradually, _increaseVolumeGradually)
    }

    fun updateDisplaySecondsInWidget(displaySeconds: Boolean) {
        updateSetting(
            displaySeconds, repository::updateDisplaySecondsInWidget, _displaySecondsInWidget)
    }

    private fun <T> updateSetting(
        value: T, updateRepository: suspend (T) -> Unit, liveData: MutableStateFlow<T>) {
        viewModelScope.launch {
            updateRepository(value)
        }
        liveData.value = value
    }
}