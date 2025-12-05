package com.olr261dn.clock.services.stopwatch.foregroundService.stopwatchServiceController.notificationWrapper

import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification.NotificationWrapper
import com.olr261dn.clock.services.stopwatch.notification.StopwatchStateNotification
import com.olr261dn.clock.services.stopwatch.notification.StopwatchStateNotification.Companion.NOTIFICATION_ID

class StopwatchStateNotificationWrapper(
    foregroundNotification: StopwatchStateNotification):
    NotificationWrapper(foregroundNotification) {

    fun dismissForegroundNotification() {
        foregroundNotification.dismissNotification(NOTIFICATION_ID)
    }
}
