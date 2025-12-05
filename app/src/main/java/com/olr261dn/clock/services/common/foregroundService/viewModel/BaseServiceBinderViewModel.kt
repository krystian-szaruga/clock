package com.olr261dn.clock.services.common.foregroundService.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olr261dn.clock.services.common.foregroundService.BaseService
import com.olr261dn.clock.services.common.foregroundService.serviceBinder.ServiceBinder
import kotlinx.coroutines.launch

abstract class BaseServiceBinderViewModel(
    application: Application,
    serviceClass: Class<out BaseService>) : ViewModel() {

    private val serviceBinder = ServiceBinder(
        application,
        serviceClass
    ) { collectServiceState(it) }

    init {
        serviceBinder.bind()
        Log.d(
            "BaseServiceBinderViewModel",
            "Service bound successfully"
        )
    }

    override fun onCleared() {
        serviceBinder.unbind()
        Log.d(
            "BaseServiceBinderViewModel",
            "Service unbound successfully"
        )
        super.onCleared()
    }
    abstract suspend fun assignData(service: BaseService)

    private fun collectServiceState(service: BaseService){
        viewModelScope.launch {
            assignData(service)
        }
    }

    protected fun getBinderService(): BaseService? {
        return serviceBinder.getService()
    }
    protected fun startForeground() {
        serviceBinder.start()
    }
}
