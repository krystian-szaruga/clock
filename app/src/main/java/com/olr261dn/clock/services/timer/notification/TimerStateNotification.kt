package com.olr261dn.clock.services.timer.notification

import android.app.Notification
import android.content.Context
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification.TimeNotification
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.NOTIFICATION_ID
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.REQUEST_CODE
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.TIMER_SERVICE

class TimerStateNotification(context: Context):
    TimeNotification(
        context, TIMER_SERVICE, REQUEST_CODE, NOTIFICATION_ID,
        ClockScreens.Timer.name, NAME, DESCRIPTION)
{
    override fun buildNotification(
        times: List<String>, actualTime: String): Notification {
        return baseNotification(
            times = times,
            title = "Timer",
            text = "Timer is running",
            inboxTitle = "Timers:")
            .build()
    }

    companion object {
        const val NAME = "Current Timer State"
        const val DESCRIPTION = "Used For Displaying Actual Timer Status"
    }
}