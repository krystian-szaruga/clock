package com.olr261dn.clock.utils.globalSettingsProvider

import android.content.Context
import androidx.room.Room
import com.olr261dn.clock.data.globalSettings.GlobalSettingsDao
import com.olr261dn.clock.data.globalSettings.GlobalSettingsDatabase
import com.olr261dn.clock.repository.GlobalSettingsRepository

class GlobalSettingsProvider(private val context: Context) {

    fun getRepository(): GlobalSettingsRepository = GlobalSettingsRepository(getDao())

    private fun getDao(): GlobalSettingsDao = getDb().globalSettingsDao()

    private fun getDb(): GlobalSettingsDatabase = Room.databaseBuilder(
        context.applicationContext,
        GlobalSettingsDatabase::class.java,
        "global_settings")
        .build()
}