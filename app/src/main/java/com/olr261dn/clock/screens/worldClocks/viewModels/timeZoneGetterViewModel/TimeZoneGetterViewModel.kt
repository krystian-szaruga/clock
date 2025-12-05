package com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.timeZonesProvider.TimeZoneFetcher
import com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.timeZonesProvider.TimeZoneFormatter
import com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.timeZonesProvider.TimeZoneOffsetProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TimeZoneGetterViewModel: ViewModel() {

    private val timeZoneOffsetProvider = TimeZoneOffsetProvider(
        TimeZoneFetcher(), TimeZoneFormatter())

    private val _timeZoneOffsets = MutableStateFlow<List<String>>(emptyList())

    private val _filteredTimeZoneOffsets = MutableStateFlow<
            List<String>>(emptyList())
    val filteredTimeZoneOffsets: MutableStateFlow<
            List<String>> get() = _filteredTimeZoneOffsets

    private val _isLoading = MutableStateFlow(false)
    val isLoading: MutableStateFlow<Boolean> get() = _isLoading

    fun fetchTimeZoneOffsets() {
        viewModelScope.launch {
            _isLoading.value = true
            _timeZoneOffsets.value = timeZoneOffsetProvider.getTimeZoneOffsets()
            _filteredTimeZoneOffsets.value = _timeZoneOffsets.value
            _isLoading.value = false
        }
    }

    fun filterTimeZoneOffsets(searchValue: String){
        _filteredTimeZoneOffsets.value = if (searchValue.isNotEmpty())
            _timeZoneOffsets.value.filter {
                it.lowercase().contains(searchValue.lowercase()) }
            else _timeZoneOffsets.value
    }
}

