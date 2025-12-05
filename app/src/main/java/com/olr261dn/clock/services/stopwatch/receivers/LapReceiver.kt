package com.olr261dn.clock.services.stopwatch.receivers

import android.content.Intent
import com.olr261dn.clock.services.stopwatch.foregroundService.StopwatchService.Companion.LEAP_INTENT

class LapReceiver : BaseActionReceiver() {
    override fun getCustomIntent(): Intent = Intent(LEAP_INTENT)
}
