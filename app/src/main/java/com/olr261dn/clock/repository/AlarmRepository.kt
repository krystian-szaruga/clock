package com.olr261dn.clock.repository

import com.olr261dn.clock.data.alarm.AlarmDatabaseDao
import com.olr261dn.clock.model.AlarmItem
import javax.inject.Inject

class AlarmRepository @Inject constructor(
    private val alarmDatabaseDao: AlarmDatabaseDao)
{
    suspend fun getAlarms(): List<AlarmItem> =
        alarmDatabaseDao.getAlarms()

    suspend fun getAlarm(id: Int): AlarmItem? =
        alarmDatabaseDao.getAlarm(id)

    suspend fun addAlarm(alarmItem: AlarmItem) =
        alarmDatabaseDao.addAlarm(alarmItem)

    suspend fun updateAlarm(alarmItem: AlarmItem) =
        alarmDatabaseDao.updateAlarm(alarmItem)

    suspend fun deleteAlarm(id: Int) =
        alarmDatabaseDao.deleteAlarm(id)

    suspend fun deleteAlarms(ids: List<Int>) =
        alarmDatabaseDao.deleteAlarms(ids)

    suspend fun getIds(): List<Int> =
        alarmDatabaseDao.getIds().sorted()
}