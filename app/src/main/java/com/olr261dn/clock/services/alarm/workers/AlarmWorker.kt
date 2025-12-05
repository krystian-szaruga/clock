package com.olr261dn.clock.services.alarm.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.serviceManager.ServiceManager
import com.olr261dn.clock.services.alarm.notificationManager.notificationLaunchers.AlarmNotification
import com.olr261dn.clock.services.common.workers.BaseWorker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class AlarmWorker @AssistedInject constructor(
    alarmRepository: AlarmRepository,
    globalSettingsRepository: GlobalSettingsRepository,
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : BaseWorker(context, params, alarmRepository, globalSettingsRepository) {

    override suspend fun handleUniqueWorkerTask() {
        val alarmFiringService = ServiceManager {
            AlarmNotification(it.applicationContext) }
            .getInstance(context.applicationContext)
        val alarm = getAlarmItem()
        setNextAlarmWrapper.setNextAlarm(alarm)
        alarmFiringService?.showNotification(alarm.id, alarm.label, alarm.time)
            ?: throw IllegalArgumentException("alarmFiringService is null")
    }
}
