package com.olr261dn.clock.services.common.fullScreenActivity

import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.olr261dn.clock.services.common.receivers.DynamicReceiverRegister

abstract class BaseFullScreenActivity(
    customIntentAction: String) : ComponentActivity()
{
    private val finishActivity = DynamicReceiverRegister(customIntentAction)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setShowWhenLocked(true)
        setTurnScreenOn(true)
        enableEdgeToEdge()
        finishActivity.registerReceiver(this) { finish() }
    }

    override fun onDestroy() {
        finishActivity.unregisterReceiver(this)
        super.onDestroy()
    }

    protected fun launchReceiver(
        actionReceiver: Class<out BroadcastReceiver>,
        id: Int? = null)
    {
        Intent(this, actionReceiver).also { intent ->
            id?.let {
                intent.putExtra("Id", it)
            }
            this.sendBroadcast(intent)
        }
        finish()
    }
}
