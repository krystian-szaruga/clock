package com.olr261dn.clock.services.alarm.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.services.alarm.alarmController.AlarmController
import com.olr261dn.clock.services.common.workers.BaseWorker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DismissUpcomingWorker @AssistedInject constructor(
    alarmRepository: AlarmRepository,
    globalSettingsRepository: GlobalSettingsRepository,
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : BaseWorker(context, params, alarmRepository, globalSettingsRepository) {

    private val alarmController = AlarmController(context)

    override suspend fun handleUniqueWorkerTask() {
        val alarm = getAlarmItem()
        alarmController.cancelAlarm(alarm)
        setNextAlarmWrapper.setNextAlarm(alarm)
    }
}
