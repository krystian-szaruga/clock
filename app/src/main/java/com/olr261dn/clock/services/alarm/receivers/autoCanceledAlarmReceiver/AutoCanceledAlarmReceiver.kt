package com.olr261dn.clock.services.alarm.receivers.autoCanceledAlarmReceiver

import android.content.Context
import android.content.Intent
import com.olr261dn.clock.services.alarm.fullScreenAlarmActivity.FullScreenAlarmActivity
import com.olr261dn.clock.services.alarm.receivers.baseReceiver.BaseAutoDismissReceiver
import com.olr261dn.clock.services.widget.utils.updateWidgetIntent

class AutoCanceledAlarmReceiver: BaseAutoDismissReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        super.onReceive(context, intent)
        val time = intent?.getStringExtra("time")?: ""
        val id = intent?.getIntExtra("Id", 0) ?: 0
        alarmFiringService?.showAutoCancelNotification("Alarm at $time")
        clearAlarmNotification.dismissEarlyAlarmNotification(context, id)
        val cancelIntent = Intent(FullScreenAlarmActivity.CANCEL_ALARM_ACTION)
        context.sendBroadcast(cancelIntent)
        updateWidgetIntent(context)
    }
}
