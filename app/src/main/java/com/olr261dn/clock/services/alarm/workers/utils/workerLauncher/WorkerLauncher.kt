package com.olr261dn.clock.services.alarm.workers.utils.workerLauncher

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager

class WorkerLauncher {

    inline fun <reified T : CoroutineWorker> launchWorker(
        context: Context?, intent: Intent?)
    {
        context?.let {
            val id = intent?.getIntExtra("Id", 0) ?: 0

            val workRequest = OneTimeWorkRequestBuilder<T>()
                .setInputData(
                    Data.Builder()
                        .putInt("Id", id)
                        .build()
                )
                .build()

            val workManager = WorkManager.getInstance(it.applicationContext)
            workManager.enqueue(workRequest)

            observeWorkStatus(workManager, workRequest, T::class.java.simpleName)
        } ?: run {
            Log.e("WorkerLauncher", "Context is null, cannot launch worker.")
        }
    }

    fun observeWorkStatus(
        workManager: WorkManager,
        workRequest: OneTimeWorkRequest,
        workerName: String)
    {
        workManager.getWorkInfoByIdLiveData(workRequest.id).observeForever {
            it?.let {
                when (it.state) {
                    WorkInfo.State.ENQUEUED -> {
                        Log.d(
                            "WorkerLauncher",
                            "$workerName is enqueued.")
                    }

                    WorkInfo.State.SUCCEEDED -> {
                        Log.d(
                            "WorkerLauncher",
                            "$workerName completed successfully.")
                    }

                    WorkInfo.State.FAILED -> {
                        Log.e(
                            "WorkerLauncher",
                            "$workerName failed.")
                    }

                    WorkInfo.State.RUNNING -> {
                        Log.d(
                            "WorkerLauncher",
                            "$workerName is running.")
                    }

                    else -> {
                        Log.d(
                            "WorkerLauncher",
                            "$workerName state: ${it.state}"
                        )
                    }
                }
            }
        }
    }
}