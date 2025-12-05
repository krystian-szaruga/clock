package com.olr261dn.clock.services.common.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.core.content.ContextCompat


class DynamicReceiverRegister(private val customIntentAction: String) {
    private lateinit var cancelActivityReceiver: BroadcastReceiver

    fun registerReceiver(
        context: Context,
        onReceiveAction: () -> Unit)
    {
        cancelActivityReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                onReceiveAction()
            }
        }
        val filter = IntentFilter(customIntentAction)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(
                cancelActivityReceiver,
                filter,
                Context.RECEIVER_EXPORTED
            )
        } else {
            ContextCompat.registerReceiver(
                context,
                cancelActivityReceiver,
                filter,
                ContextCompat.RECEIVER_EXPORTED
            )
        }
    }
    fun unregisterReceiver(context: Context) {
        context.unregisterReceiver(cancelActivityReceiver)
    }
}