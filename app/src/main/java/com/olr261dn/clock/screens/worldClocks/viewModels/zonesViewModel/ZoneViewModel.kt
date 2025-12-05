package com.olr261dn.clock.screens.worldClocks.viewModels.zonesViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olr261dn.clock.model.Zone
import com.olr261dn.clock.repository.ZoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneViewModel @Inject constructor(
    private val repository: ZoneRepository): ViewModel()
{
    private val _zones = MutableStateFlow<List<Zone>>(listOf())
    val zones = _zones.asStateFlow()

    init {
        viewModelScope.launch {
            setZones()
        }
    }

    fun addZone(timeZone: String) {
        viewModelScope.launch { 
            repository.insertZone(Zone(timeZone))
            setZones()
        }
    }

    fun deleteZone(timeZone: String){
        viewModelScope.launch {
            repository.deleteZone(Zone(timeZone))
            setZones()
        }
    }
    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
            setZones()
        }
    }

    fun resetZones(){
        viewModelScope.launch {
            setZones()
        }
    }

    private suspend fun setZones(){
        _zones.value = repository.getZones()
    }




}