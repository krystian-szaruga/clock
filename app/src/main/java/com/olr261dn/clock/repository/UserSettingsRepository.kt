package com.olr261dn.clock.repository

import com.olr261dn.clock.data.userSettings.UserSettingsDao
import com.olr261dn.clock.model.UserSettings
import com.olr261dn.clock.screens.timer.components.utils.TimerDuration
import javax.inject.Inject

class UserSettingsRepository @Inject constructor(
    private val userSettingsDao: UserSettingsDao)
{
    suspend fun getRowCount() = userSettingsDao.getRowCount()

    suspend fun addUserSettings(userSettings: UserSettings) {
        userSettingsDao.addUserSettings(userSettings.copy(id = 1))
    }

    suspend fun getUserSettings() = userSettingsDao.getUserSettings()

    suspend fun addUseAnalogClock(useAnalogClock: Boolean) {
        userSettingsDao.updateShowAnalogClock(useAnalogClock)
    }

    suspend fun updateDuration(duration: TimerDuration){
        userSettingsDao.updateDuration(duration)
    }

    suspend fun updateLastScreenName(lastScreenName: String){
        userSettingsDao.updateLastScreen(lastScreenName)
    }


}

