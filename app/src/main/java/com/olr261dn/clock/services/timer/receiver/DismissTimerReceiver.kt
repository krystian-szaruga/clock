package com.olr261dn.clock.services.timer.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.ALARM_TIMER_END_ID
import com.olr261dn.clock.services.timer.notification.TimerConst.Companion.NOTIFICATION_ID
import com.olr261dn.clock.services.timer.notification.TimerEndNotification


class DismissTimerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val timerEndNotification = TimerEndNotification(context)
        timerEndNotification.dismissNotification(ALARM_TIMER_END_ID)
        timerEndNotification.dismissNotification(NOTIFICATION_ID)
    }
}
