package com.olr261dn.clock.services.timer.foregroundService

import com.olr261dn.clock.services.common.foregroundService.BaseService
import com.olr261dn.clock.services.timer.foregroundService.timerServiceController.TimerServiceController
import com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper.TimerStateNotificationWrapper
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.NOTIFICATION_ID
import com.olr261dn.clock.services.timer.notification.TimerStateNotification


class TimerService : BaseService(NOTIFICATION_ID) {
    val timerServiceController: TimerServiceController =
        TimerServiceController(foregroundDisabler = { foregroundDisabler() })

    override fun setWrapper() {
        notificationWrapper = TimerStateNotificationWrapper(TimerStateNotification(this))
    }

    override fun createNotificationContent(): Pair<List<String>, String> =
        timerServiceController.buildNotificationText()

    override fun initializeWrapper() {
        getWrapper()?.let{ timerServiceController.initializeWrapper(it) }
    }
    override fun cancelTimers() {
        timerServiceController.timers.value.forEach {
            it.counterWrapper.stop()
        }
    }

    override fun getWrapper(): TimerStateNotificationWrapper? =
        notificationWrapper as? TimerStateNotificationWrapper
            ?: logOnNull("TimerService").let { null }

}
