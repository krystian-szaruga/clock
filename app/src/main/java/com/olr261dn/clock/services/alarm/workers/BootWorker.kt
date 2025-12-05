package com.olr261dn.clock.services.alarm.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.ClearAlarmNotification
import com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.serviceManager.ServiceManager
import com.olr261dn.clock.services.common.appNotification.MissedAlarmNotification
import com.olr261dn.clock.services.common.workers.BaseWorker
import com.olr261dn.clock.services.widget.utils.updateWidgetIntent
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.ZoneId


@HiltWorker
class BootWorker @AssistedInject constructor(
    alarmRepository: AlarmRepository,
    globalSettingsRepository: GlobalSettingsRepository,
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : BaseWorker(context, params, alarmRepository, globalSettingsRepository) {

    private val clearAlarmNotification = ClearAlarmNotification()
    override fun getBootFlag() = true

    override suspend fun handleUniqueWorkerTask() {
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        val missedAlarmNotification = ServiceManager{
            MissedAlarmNotification(it.applicationContext) }
            .getInstance(context.applicationContext)
        val pastAlarms = alarms.filter { it.isEnabled && it.time.isBefore(currentTime) }
        if (pastAlarms.isNotEmpty()) {
            missedAlarmNotification?.showMissedNotifications(
                pastAlarms.map { it.time })
        }
        alarms.filter { it.isEnabled }.forEach{
            Log.d("TAG", "handleUniqueWorkerTask: $it")
            clearAlarmNotification.dismissEarlyAndAlarmNotification(
                context, it.id)
            setNextAlarmWrapper.setNextAlarm(it)
        }
        delay(10000)
        updateWidgetIntent(context)
    }
}
