package com.olr261dn.clock.screens.alarm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olr261dn.clock.components.actionState.Result
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.screens.alarm.viewModel.utils.UniqueIdGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val repository: AlarmRepository): ViewModel()
{
    private val _alarms = MutableStateFlow<Result<List<AlarmItem>>>(Result.Loading)
    val alarms: StateFlow<Result<List<AlarmItem>>> = _alarms.asStateFlow()

    private val ids = MutableStateFlow<List<Int>>(listOf())
    private var currentAlarms = emptyList<AlarmItem>()

    private val _alarmsList = MutableStateFlow<List<AlarmItem>>(emptyList())
    val alarmList = _alarmsList.asStateFlow()

    init {
        refresh()
    }

    fun refresh(){
        loadAlarms()
        fetchIds()
    }

    private fun loadAlarms(){
        viewModelScope.launch {
            try {
                val data = repository.getAlarms()
                _alarms.value = Result.Success(data
                    .sortedBy { it.time })
                _alarmsList.value = data
            } catch (e: Exception){
                _alarms.value = Result.Error(
                    e.message ?: "Failed to Load alarms")
            }
        }
    }

//    fun getAlarms(){
//        viewModelScope.launch {
//            _alarmsList.value = repository.getAlarms()
//        }
//    }

    fun addAlarmItem(alarmItem: AlarmItem) {
        viewModelScope.launch {
            try {
                setCurrentAlarms()
                _alarms.value = Result.Loading
                repository.addAlarm(alarmItem)
                updateAlarmList(alarmItem)
            } catch (e: Exception){
                _alarms.value = Result.Error(
                    e.message ?: "Failed to Add An Alarm")
            }
        }
    }

    fun updateAlarmItem(alarmItem: AlarmItem) {
        viewModelScope.launch {
            try {
                setCurrentAlarms()
                repository.updateAlarm(alarmItem)
                _alarms.value = Result.Success(
                    currentAlarms.map {
                        if (it.id == alarmItem.id) alarmItem else it } )
            } catch (e: Exception){
                _alarms.value = Result.Error(
                    e.message ?: "Failed to Create Alarm")
            }
        }
    }

    private fun setCurrentAlarms() {
        currentAlarms = ( _alarms.value as? Result.Success )?.data.orEmpty()
    }

    fun deleteAlarmItems(alarmItems: List<AlarmItem>) {
        try {
            viewModelScope.launch {
                setCurrentAlarms()
                val alarmsToDelete = alarmItems.flatMap { listOf(it.id, it.id * -1) }
                repository.deleteAlarms(alarmsToDelete)
                _alarms.value = Result.Success(
                    currentAlarms.filterNot { it.id in alarmsToDelete })
            }
        } catch (e: Exception) {
            _alarms.value = Result.Error(
                e.message ?: "Failed To Delete Alarms")
        }
    }

    fun deleteAlarmItem(id: Int) {
        try {
            viewModelScope.launch {
                setCurrentAlarms()
                _alarms.value = Result.Success(
                    currentAlarms.filterNot { it.id == id })
                repository.deleteAlarm(id)
            }
        } catch (e: Exception) {
            _alarms.value = Result.Error(
                e.message ?: "Failed To Delete Alarm: $id"
            )
        }
    }

    private fun updateAlarmList(newAlarm: AlarmItem) {
        val updatedAlarms = when {
            currentAlarms.any { it.id == newAlarm.id } -> {
                currentAlarms.map { if (it.id == newAlarm.id) newAlarm else it } }
            else -> {
                currentAlarms + newAlarm } }.sortedBy { it.time }
        _alarms.value = Result.Success(updatedAlarms)
        fetchIds()
    }

    fun getUniqueId(): Int {
        fetchIds()
        return UniqueIdGenerator(ids.value).getId()
    }

    private fun fetchIds() {
        viewModelScope.launch {
            ids.value = repository.getIds()
        }
    }
}
