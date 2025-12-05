package com.olr261dn.clock.services.timer.receiver

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseAutoDismissReceiver
import com.olr261dn.clock.services.timer.fullScreenTimerFinishedActivity.TimerFinishedActivity

class AutoDismissTimerReceiver: BaseAutoDismissReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        super.onReceive(context, intent)
        alarmFiringService?.showAutoCancelNotification("Timer")
        val cancelIntent = Intent(TimerFinishedActivity.CANCEL_TIMER_ACTION)
        val dismissIntent = Intent(context, DismissTimerReceiver::class.java)
        context.sendBroadcast(cancelIntent)
        context.sendBroadcast(dismissIntent)
    }
}
