package com.olr261dn.clock.services.common.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.model.GlobalSettings
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.services.alarm.workers.utils.recurringAlarmManager.SetNextAlarmWrapper

abstract class BaseWorker(
    protected val context: Context,
    params: WorkerParameters,
    protected val alarmRepository: AlarmRepository,
    private val globalSettingsRepository: GlobalSettingsRepository,
) : CoroutineWorker(context, params) {

    protected val setNextAlarmWrapper = SetNextAlarmWrapper(
        alarmRepository, context.applicationContext, getBootFlag())
    protected var alarms = emptyList<AlarmItem>()
    protected var id = 0
    protected lateinit var globalSettings : GlobalSettings
    protected open fun getBootFlag() = false

    override suspend fun doWork(): Result {
        val workerTag = this::class.simpleName ?: "BaseWorker"
        return try {
            setAlarmData()
            setEarlyAlarmReminderFlag()
            handleUniqueWorkerTask()
            Log.d(workerTag, "Success")
            Result.success()
        } catch (e: Exception) {
            Log.d(workerTag, "E: An error occurred")
            Log.e(workerTag, "Error: ${e.message}", e)
            Result.failure()
        }
    }

    private suspend fun setAlarmData(){
        id = inputData.getInt("Id", 0)
        alarms = alarmRepository.getAlarms()
    }

    private suspend fun setEarlyAlarmReminderFlag(){
        globalSettings = globalSettingsRepository.getGlobalSettings()
        setNextAlarmWrapper.addEarlyAlarmReminderFlag(globalSettings.earlyAlarmReminder)
    }

    abstract suspend fun handleUniqueWorkerTask()

    protected fun getAlarmItem(): AlarmItem =
        validateAlarmItem(alarms.find { it.id == id })

    private fun validateAlarmItem(alarm: AlarmItem?): AlarmItem {
        if (alarm != null) {
            return alarm
        } else {
            throw IllegalStateException("No alarm found with ID: $id")
        }
    }
}