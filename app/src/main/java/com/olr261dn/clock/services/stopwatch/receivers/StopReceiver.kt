package com.olr261dn.clock.services.stopwatch.receivers

import android.content.Intent
import com.olr261dn.clock.services.stopwatch.foregroundService.StopwatchService.Companion.STOP_INTENT

class StopReceiver : BaseActionReceiver() {
    override fun getCustomIntent(): Intent = Intent(STOP_INTENT)
}
