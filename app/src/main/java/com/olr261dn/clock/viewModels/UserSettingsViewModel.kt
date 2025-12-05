package com.olr261dn.clock.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olr261dn.clock.model.UserSettings
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.repository.UserSettingsRepository
import com.olr261dn.clock.screens.timer.components.utils.TimerDuration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    private val repository: UserSettingsRepository) : ViewModel()
{
    private val _duration = MutableStateFlow(TimerDuration())
    val duration = _duration.asStateFlow()
    private val _lastScreenName = MutableStateFlow<String?>(null)
    val lastScreenName = _lastScreenName.asStateFlow()
    private val _showAnalogClock = MutableStateFlow(false)
    val showAnalogClock = _showAnalogClock.asStateFlow()

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch {
            if (repository.getRowCount() == 0) {
                repository.addUserSettings(UserSettings())
            }
            val dataFromRepo = repository.getUserSettings()
            _showAnalogClock.value = dataFromRepo.showAnalogClock
            _duration.value = dataFromRepo.timerDuration
            _lastScreenName.value = dataFromRepo.lastScreenName
        }
    }

    fun updateTimerValues() {
        viewModelScope.launch {
            repository.updateDuration(_duration.value)
        }
    }

    fun updateShowAnalogClock(showAnalogClock: Boolean) {
        viewModelScope.launch {
            repository.addUseAnalogClock(useAnalogClock = showAnalogClock)
        }
        _showAnalogClock.value = showAnalogClock
    }

    fun updateDurationValue(duration: TimerDuration) {
        viewModelScope.launch {
            _duration.value = duration
        }
    }

    fun updateHourValue(value: Int) {
        viewModelScope.launch {
            _duration.value = _duration.value.copy(
                hour = value, hourIndex = value + 2
            )
        }
    }

    fun updateMinuteValue(value: Int) {
        viewModelScope.launch {
            _duration.value = _duration.value.copy(
                minute = value, minuteIndex = value + 2
            )
        }
    }

    fun updateSecondValue(value: Int) {
        viewModelScope.launch {
            _duration.value = _duration.value.copy(
                second = value, secondIndex = value + 2
            )
        }
    }

    fun updateLastScreen(lastScreenName: String) {
        if (lastScreenName !in excludedScreens) {
            _lastScreenName.value = lastScreenName
            viewModelScope.launch {
                repository.updateLastScreenName(lastScreenName)
            }
        }
    }

    companion object {
        private val excludedScreens = setOf(
            ClockScreens.NotificationPermission.name,
            ClockScreens.Settings.name)
    }
}
