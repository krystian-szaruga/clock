package com.olr261dn.clock.services.stopwatch.receivers

import android.content.Intent
import com.olr261dn.clock.services.stopwatch.foregroundService.StopwatchService.Companion.CONTINUE_INTENT

class ContinueReceiver : BaseActionReceiver() {
    override fun getCustomIntent(): Intent = Intent(CONTINUE_INTENT)
}
