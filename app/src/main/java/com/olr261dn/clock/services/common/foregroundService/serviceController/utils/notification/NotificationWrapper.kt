package com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification

import android.app.Notification

abstract class NotificationWrapper(
    protected val foregroundNotification: TimeNotification)
{
    fun startForegroundNotification(
        content: Pair<List<String>, String>): Notification =
        foregroundNotification.startNotification(content.first, content.second)


    fun updateForegroundNotification(
        content: Pair<List<String>, String>) {
        foregroundNotification.updateNotification(content.first, content.second)
    }


}
