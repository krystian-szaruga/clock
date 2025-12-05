package com.olr261dn.clock.services.stopwatch.foregroundService

import com.olr261dn.clock.services.common.foregroundService.BaseService
import com.olr261dn.clock.services.common.receivers.DynamicReceiverRegister
import com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchServiceController.StopwatchServiceController
import com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchServiceController.notificationWrapper.StopwatchStateNotificationWrapper
import com.olr261dn.clock.services.stopwatch.notification.StopwatchStateNotification
import com.olr261dn.clock.services.stopwatch.notification.StopwatchStateNotification.Companion.NOTIFICATION_ID

class StopwatchService : BaseService(NOTIFICATION_ID) {

    val stopwatchServiceController: StopwatchServiceController =
        StopwatchServiceController(
            foregroundDisabler = { foregroundDisabler() })

    private val lapDynamicRegister = DynamicReceiverRegister(LEAP_INTENT)
    private val stopDynamicRegister = DynamicReceiverRegister(STOP_INTENT)
    private val continueDynamicRegister = DynamicReceiverRegister(CONTINUE_INTENT)

    override fun registerReceivers() {
        lapDynamicRegister.registerReceiver(this) {
            stopwatchServiceController.getLap()
        }
        stopDynamicRegister.registerReceiver(this){
            stopwatchServiceController.stopTimer()
        }
        continueDynamicRegister.registerReceiver(this) {
            stopwatchServiceController.startTimer()
        }
    }

    override fun unregisterReceivers() {
        lapDynamicRegister.unregisterReceiver(this)
        stopDynamicRegister.unregisterReceiver(this)
        continueDynamicRegister.unregisterReceiver(this)
    }

    override fun setWrapper() {
        notificationWrapper = StopwatchStateNotificationWrapper(
            StopwatchStateNotification(this)
        )
    }

    override fun createNotificationContent(): Pair<List<String>, String> =
        stopwatchServiceController.buildNotificationText()

    override fun initializeWrapper(){
        getWrapper()?.let { stopwatchServiceController.initializeWrapper(it) }
    }

    override fun cancelTimers() {
        stopwatchServiceController.stopwatch.value.counterWrapper.stop()
    }

    override fun getWrapper(): StopwatchStateNotificationWrapper? =
        notificationWrapper as? StopwatchStateNotificationWrapper
            ?: logOnNull("StopwatchService").let { null }

    companion object{
        const val LEAP_INTENT = "com.olr261dn.clock.ACTION_LEAP_EVENT"
        const val STOP_INTENT = "com.olr261dn.clock.ACTION_STOP_EVENT"
        const val CONTINUE_INTENT = "com.olr261dn.clock.ACTION_CONTINUE_EVENT"
    }
}


