package com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper

import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification.NotificationWrapper
import com.olr261dn.clock.services.timer.foregroundService.timerServiceController.notificationWrapper.utils.getActiveTimers.GetActiveTimers
import com.olr261dn.clock.services.timer.foregroundService.timerState.TimerState
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.NOTIFICATION_ID
import com.olr261dn.clock.services.timer.notification.TimerStateNotification


class TimerStateNotificationWrapper(
    foregroundNotification: TimerStateNotification):
    NotificationWrapper(foregroundNotification)
{
    private val getActiveTimers = GetActiveTimers()

    fun dismissForegroundNotification(
        timers: List<TimerState>,
        getFlag: (Boolean) -> Unit)
    {
        val activeTimers = getActiveTimers.getActiveTimers(timers)
        if (activeTimers.isEmpty()){
            foregroundNotification.dismissNotification(NOTIFICATION_ID)
            getFlag(true)
        } else {
            getFlag(false)
        }
    }
}
