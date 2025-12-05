package com.olr261dn.clock.services.common.foregroundService

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.util.Log
import com.olr261dn.clock.services.common.foregroundService.serviceController.utils.notification.NotificationWrapper


abstract class BaseService(private val notificationId: Int) : Service() {


    private val binder = LocalBinder()
    private var foregroundRunning: Boolean = false
    protected var notificationWrapper: NotificationWrapper? = null

    protected abstract fun setWrapper()

    protected abstract fun createNotificationContent(): Pair<List<String>, String>

    protected abstract fun initializeWrapper()

    protected abstract fun cancelTimers()

    protected open fun registerReceivers() {

    }

    protected open fun unregisterReceivers() {

    }

    protected abstract fun getWrapper(): NotificationWrapper?

    protected fun foregroundDisabler() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
        foregroundRunning = false
    }

    protected fun logOnNull(tag: String) {
        Log.e(tag, "NotificationWrapper has not been initialized")
    }

    private fun serviceWrapperInitialization(){
        setWrapper()
        initializeWrapper()
    }

    override fun onBind(intent: Intent?): Binder {
        return binder
    }

    override fun onCreate() {
        registerReceivers()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceWrapperInitialization()
        if (!foregroundRunning) {
            getWrapper()?.startForegroundNotification(
                createNotificationContent()
            ).let {
                startForeground(notificationId, it)
                foregroundRunning = true
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        unregisterReceivers()
        cancelTimers()
        super.onDestroy()
    }

    inner class LocalBinder : Binder() {
        fun getService(): BaseService = this@BaseService
    }

}