package com.olr261dn.clock.services.timer.receiver

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseReceiver
import com.olr261dn.clock.services.timer.notification.TimerEndNotification


class TimerEndReceiver : BaseReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val timerEndNotification = TimerEndNotification(context)
        timerEndNotification.showNotification()
    }
}