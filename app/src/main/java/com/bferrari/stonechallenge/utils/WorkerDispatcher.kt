package com.bferrari.stonechallenge.utils

import android.content.Context
import android.util.Log
import androidx.work.*
import com.bferrari.data.AppApiService
import com.bferrari.data.datasource.FactsDataSource
import com.bferrari.data.datasource.FactsRepository
import com.bferrari.stonechallenge.extensions.add
import com.bferrari.stonechallenge.injection.Modules.get
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

object WorkerDispatcher {
    fun dispatch(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

    fun clearDisposables() {
        NotificationWorker.disposable.clear()
    }
}

class NotificationWorker(private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    private val factsRepository: FactsDataSource = get()

    override fun doWork(): Result {
        factsRepository.getSingleFact()
            .subscribeOn(Schedulers.io())
            .subscribe { fact ->
                fact?.let { PushNotificationManager.show(appContext, NOTIFICATION_ID, it.value) }
            }.add(disposable)

        return Result.success()
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        val disposable = CompositeDisposable()
    }
}