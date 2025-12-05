package com.olr261dn.clock.services.common.foregroundService.serviceBinder

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat
import com.olr261dn.clock.services.common.foregroundService.BaseService
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

class ServiceBinder<T : BaseService>(
    private val application: Application,
    private val serviceClass: Class<T>,
    private val onServiceConnected: (T) -> Unit
) {
    private var service: WeakReference<T>? = null
    private val intent: Intent = Intent(application, serviceClass)
    private var isBound = AtomicBoolean(false)
    companion object {
        private const val TAG = "ServiceBinder"
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d(TAG, "onServiceConnected: $service #$$#$#")
            @Suppress("UNCHECKED_CAST")
            val serviceInstance = (binder as? BaseService.LocalBinder)
                ?.getService() as? T
            serviceInstance?.let {
                service = WeakReference(it)
                onServiceConnected(it)
                isBound.set(true)
            } ?: run {
                Log.e(
                    TAG,
                    "Service connected but instance is null or " +
                            "wrong type for ${serviceClass.simpleName}.")
            }

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            service = null
            isBound.set(false)
        }
    }

    fun bind() {
        if (isBound.compareAndSet(false, true)) {
            application.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            Log.d(TAG, "onServiceConnected: $service")
            Log.d(TAG, "onServiceConnected: $serviceClass")
        } else {
            Log.w(TAG, "Service is already bound.")
        }
    }

    fun unbind() {
        if (isBound.compareAndSet(true, false)) {
            application.unbindService(serviceConnection)
        } else {
            Log.w(TAG, "Service is not bound.")
        }
    }

    fun start(){
        ContextCompat.startForegroundService(application, intent)
    }

    fun getService(): T? = service?.get() ?: run {
        Log.e(TAG, "Service Is Not Available")
        null
    }
}
