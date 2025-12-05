package com.olr261dn.clock.services.alarm.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.serviceManager.ServiceManager
import com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers.EarlyAlarmNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class EarlyAlarmWorker @AssistedInject constructor(
    private val repository: AlarmRepository,
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val earlyAlarmFiringService = ServiceManager {
                EarlyAlarmNotification(it.applicationContext) }
                .getInstance(context.applicationContext)
            val alarm = getAlarmItem()
            if (alarm != null) {
                earlyAlarmFiringService?.showNotification(
                    alarm.id, alarm.label, alarm.time) }
            Result.success()
        } catch (e: Exception){
            return Result.failure()
        }
    }
    private suspend fun getAlarmItem(): AlarmItem? {
        val id = inputData.getInt("Id", 0)
        val alarm = repository.getAlarm(id)
        return alarm
    }


}