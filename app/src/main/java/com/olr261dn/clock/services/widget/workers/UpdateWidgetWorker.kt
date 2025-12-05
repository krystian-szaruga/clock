package com.olr261dn.clock.services.widget.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.services.common.workers.BaseWorker
import com.olr261dn.clock.services.widget.ClockWidget
import com.olr261dn.clock.utils.timeConverters.localDateTime.TimeToStringWithPattern
import com.olr261dn.clock.utils.timeConverters.timePatternResolver.TimePatternResolver
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UpdateWidgetWorker @AssistedInject constructor(
    alarmRepository: AlarmRepository,
    globalSettingsRepository: GlobalSettingsRepository,
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
): BaseWorker(context, params, alarmRepository, globalSettingsRepository) {

    override suspend fun handleUniqueWorkerTask() {
        val clockWidget = ClockWidget()
        val filteredAlarms = alarms.sortedBy { it.time }.filter { it.isEnabled }
        val timeFormatType = globalSettings.timeFormat
        val displaySeconds = globalSettings.displaySecondsInWidget
        val alarmTimeFormat = TimePatternResolver(timeFormatType).getFormat()
        val timeFormat = TimePatternResolver(timeFormatType, displaySeconds).getFormat()
        val timeFormatWithDate = "$alarmTimeFormat EEE, dd MMM yyyy"
        val alarmTime = if (filteredAlarms.isNotEmpty())
            TimeToStringWithPattern(filteredAlarms[0].time, timeFormatWithDate).convert() else null
        clockWidget.updateWidget(context.applicationContext, alarmTime, timeFormat)
    }
}
