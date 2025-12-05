package com.olr261dn.clock.di

import android.content.Context
import androidx.room.Room
import com.olr261dn.clock.data.alarm.AlarmDatabase
import com.olr261dn.clock.data.alarm.AlarmDatabaseDao
import com.olr261dn.clock.data.globalSettings.GlobalSettingsDao
import com.olr261dn.clock.data.globalSettings.GlobalSettingsDatabase
import com.olr261dn.clock.data.timeZone.ZoneDatabase
import com.olr261dn.clock.data.timeZone.ZoneDatabaseDao
import com.olr261dn.clock.data.userSettings.UserSettingsDao
import com.olr261dn.clock.data.userSettings.UserSettingsDatabase
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideZoneDao(zoneDatabase: ZoneDatabase): ZoneDatabaseDao =
        zoneDatabase.zoneDao()

    @Singleton
    @Provides
    fun provideZoneDatabase(@ApplicationContext context: Context): ZoneDatabase =
        Room.databaseBuilder(
            context,
            ZoneDatabase::class.java,
            "zone_ids")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideAlarmDao(alarmDatabase: AlarmDatabase): AlarmDatabaseDao =
        alarmDatabase.alarmDatabaseDao()

    @Singleton
    @Provides
    fun provideAlarmDatabase(@ApplicationContext context: Context): AlarmDatabase =
        Room.databaseBuilder(
            context,
            AlarmDatabase::class.java,
            "alarm")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserSettingsDao(userSettingsDatabase: UserSettingsDatabase):
            UserSettingsDao =
        userSettingsDatabase.userSettingsDao()

    @Singleton
    @Provides
    fun provideUserSettingsDatabase(@ApplicationContext context: Context):
            UserSettingsDatabase =
        Room.databaseBuilder(
            context,
            UserSettingsDatabase::class.java,
            "user_settings")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAlarmRepository(
        alarmDatabaseDao: AlarmDatabaseDao
    ): AlarmRepository {
        return AlarmRepository(alarmDatabaseDao)
    }

    @Singleton
    @Provides
    fun provideGlobalSettingsDao(globalSettingsDatabase: GlobalSettingsDatabase):
            GlobalSettingsDao =
        globalSettingsDatabase.globalSettingsDao()

    @Singleton
    @Provides
    fun provideGlobalSettingsDatabase(@ApplicationContext context: Context):
            GlobalSettingsDatabase =
        Room.databaseBuilder(
            context,
            GlobalSettingsDatabase::class.java,
            "global_settings")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideGlobalSettingsRepository(
        globalSettingsDao: GlobalSettingsDao
    ): GlobalSettingsRepository {
        return GlobalSettingsRepository(globalSettingsDao)
    }
}
