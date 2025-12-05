package com.olr261dn.clock.services.alarm.notificationManager.notificationDismissal.serviceManager

import android.content.Context


class ServiceManager<T>(
    private val factory: (Context) -> T) {

    fun getInstance(context: Context?): T? {
        val safeContext = context?.applicationContext ?: return null
        return factory(safeContext)
    }
}
