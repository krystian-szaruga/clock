package com.olr261dn.clock.services.alarm.workers


import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.olr261dn.clock.model.AlarmItem
import com.olr261dn.clock.repository.AlarmRepository
import com.olr261dn.clock.repository.GlobalSettingsRepository
import com.olr261dn.clock.services.alarm.alarmController.AlarmController
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

@HiltWorker
class SnoozeWorker @AssistedInject constructor(
    private val alarmRepository: AlarmRepository,
    private val globalSettingsRepository: GlobalSettingsRepository,
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters) : CoroutineWorker(context, params)
{
    private val alarmController = AlarmController(context)

    override suspend fun doWork(): Result {
        val id = inputData.getInt("Id", 0)
        val alarm = alarmRepository.getAlarm(id.absoluteValue)
        val globalSettings = globalSettingsRepository.getGlobalSettings()
        val adjustedSnoozeTime = getSnoozeTime(
            id, extractNumber(globalSettings.snoozeTime), alarm)
        val newAlarm = alarm?.copy(
            time = adjustedSnoozeTime,
            isSnooze = true,
            isEnabled = true,
            id = alarm.id * -1,
            isRecurring = false)
            ?: AlarmItem(
                time = adjustedSnoozeTime,
                isSnooze = true,
                isEnabled = true,
                id = id,
                isRecurring = false
            )
        alarm?.let {
            val updatedAlarm = it.copy(snoozeTime = adjustedSnoozeTime)
            alarmRepository.updateAlarm(updatedAlarm)
        }
        alarmRepository.addAlarm(newAlarm)
        alarmController.setAlarm(newAlarm, globalSettings.earlyAlarmReminder)

        return Result.success()
    }

    private fun getSnoozeTime(
        id: Int,
        snoozeMinutes: Long,
        alarm: AlarmItem?): LocalDateTime
    {
        val currentTime = LocalDateTime.now()
        val snoozeTime = when {
            id < 0 -> alarm?.snoozeTime?.plusMinutes(snoozeMinutes)
            else -> alarm?.time?.plusMinutes(snoozeMinutes)
        } ?: LocalDateTime.now()
            .withSecond(0)
            .withMinute(snoozeMinutes.toInt())

        val dayOffset = ChronoUnit.DAYS.between(currentTime, snoozeTime)
        val adjustedSnoozeTime = snoozeTime.minusDays(dayOffset)
        return adjustedSnoozeTime
    }

    private fun extractNumber(input: String): Long {
        val regex = Regex("""\d+""")
        val matchResult = regex.find(input)
        return matchResult?.value?.toLongOrNull() ?: 10L
    }
}
