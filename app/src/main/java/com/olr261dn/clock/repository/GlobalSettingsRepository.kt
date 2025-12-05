package com.olr261dn.clock.repository

import com.olr261dn.clock.data.globalSettings.GlobalSettingsDao
import com.olr261dn.clock.model.GlobalSettings
import javax.inject.Inject

class GlobalSettingsRepository @Inject constructor(
    private val globalSettingsDao: GlobalSettingsDao) {

    suspend fun getGlobalSettings(): GlobalSettings {
        if (getRowCount() == 0) addGlobalSettings(GlobalSettings())
        return globalSettingsDao.getGlobalSettings()
    }

    private suspend fun getRowCount() = globalSettingsDao.getRowCount()

    private suspend fun addGlobalSettings(globalSettings: GlobalSettings) {
        globalSettingsDao.addGlobalSettings(globalSettings.copy(id = 1))
    }

    suspend fun updateEarlyAlarmReminder(earlyAlarmReminder: Boolean) {
        globalSettingsDao.updateEarlyAlarmReminder(earlyAlarmReminder)
    }

    suspend fun updateShowAlarmInfo(showAlarmInfo: Boolean) {
        globalSettingsDao.updateShowAlarmInfo(showAlarmInfo)
    }

    suspend fun updateSnoozeTime(snoozeTime: String) {
        globalSettingsDao.updateSnoozeTime(snoozeTime)
    }

    suspend fun updateTimeFormat(timeFormat: String) {
        globalSettingsDao.updateTimeFormat(timeFormat)
    }

    suspend fun updateIncreaseVolumeGradually(value: Boolean) {
        globalSettingsDao.updateIncreaseVolumeGradually(value)
    }

    suspend fun updateDisplaySecondsInWidget(value: Boolean) {
        globalSettingsDao.updateDisplaySecondsInWidget(value)
    }
}

