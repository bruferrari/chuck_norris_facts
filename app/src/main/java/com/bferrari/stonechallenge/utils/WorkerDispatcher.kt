package com.bferrari.stonechallenge.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

object WorkerDispatcher {
    fun dispatch(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(15, TimeUnit.SECONDS) //TODO: Change to minutes
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}

class NotificationWorker(private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        //TODO: add real fact here
        PushNotificationManager.show(appContext, NOTIFICATION_ID, "message from worker dispatcher")
        return Result.success()
    }

    companion object {
        private const val NOTIFICATION_ID = 1
    }
}