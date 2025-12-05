package com.olr261dn.clock.services.widget.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.olr261dn.clock.utils.other.updateWidgetIntentName

class TriggerWidgetUpdate: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val customIntent = Intent(context, ClockWidgetReceiver::class.java)
        customIntent.action = updateWidgetIntentName()
        context?.sendBroadcast(customIntent)
    }
}