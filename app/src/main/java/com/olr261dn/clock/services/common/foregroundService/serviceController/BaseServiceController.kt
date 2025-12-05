package com.olr261dn.clock.services.common.foregroundService.serviceController

import com.olr261dn.clock.services.common.foregroundService.baseState.BaseState
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification.NotificationWrapper
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.timerController.TimerController

abstract class BaseServiceController<
        T: NotificationWrapper,
        TState: BaseState>(
    protected val foregroundDisabler: () -> Unit) {

    protected var notificationWrapper: T? = null
    protected val timerController = TimerController<TState>()

    fun initializeWrapper(wrapper: T) {
        notificationWrapper = wrapper
    }
    abstract fun buildNotificationText(): Pair<List<String>, String>

}
