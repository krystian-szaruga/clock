package com.olr261dn.clock.screens.clock.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.TimePatternResolver
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.timeFormatType.TimeFormatType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ActualTimeViewModel: ViewModel() {

    private val _currentDate = MutableStateFlow("")
    val currentDate = _currentDate.asStateFlow()
    private val _currentTime = MutableStateFlow("")
    val currentTime = _currentTime.asStateFlow()
    private val _currTimeMills = MutableStateFlow<ZonedDateTime>(ZonedDateTime.now())
    val currTimeMills = _currTimeMills.asStateFlow()
    private val timePattern = MutableStateFlow(
        TimePatternResolver(TimeFormatType.SYSTEM).getFormat())

    fun startTime(defaultTimeFormat: String, zoneName: String? = null) {
        updateTimeFormat(defaultTimeFormat)
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                val dateTime = if (zoneName == null) ZonedDateTime.now() else
                    ZonedDateTime.now(ZoneId.of(zoneName))
                updateDateTime(dateTime)
                delay(1000L)
            }
        }
    }

    private fun updateTimeFormat(defaultTimeFormat: String) {
        timePattern.value = when (defaultTimeFormat) {
            "HH:mm" -> "HH:mm:ss"
            "hh:mm a" -> "hh:mm:ss a"
            else -> defaultTimeFormat
        }
    }

    private fun updateDateTime(dateTime: ZonedDateTime) {
        val formatterDate = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
        val formatterTime = DateTimeFormatter.ofPattern(timePattern.value)
        _currentDate.value = dateTime.format(formatterDate)
        _currentTime.value  = dateTime.format(formatterTime)
        _currTimeMills.value = dateTime
    }
}