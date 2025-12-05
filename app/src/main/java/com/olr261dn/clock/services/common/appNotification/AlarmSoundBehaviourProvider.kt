package com.olr261dn.clock.services.common.appNotification

import android.content.Context
import com.olr261dn.clock.utils.globalSettingsProvider.GlobalSettingsProvider
import kotlinx.coroutines.runBlocking

class AlarmSoundBehaviourProvider (context: Context) {
    private val repository = GlobalSettingsProvider(context).getRepository()
    private var increaseVolumeGradually = true

    fun getIncreaseVolumeFlag(): Boolean {
        loadSettings()
        return increaseVolumeGradually
    }

    private fun loadSettings() {
        runBlocking {
            increaseVolumeGradually = repository.getGlobalSettings().increaseVolumeGradually
        }
    }
}