package com.bferrari.stonechallenge

import android.app.Application
import com.bferrari.data.injection.apiModule
import com.bferrari.data.injection.dataModule
import com.bferrari.framework.injection.frameworkModule
import com.bferrari.stonechallenge.injection.appModule
import com.bferrari.stonechallenge.utils.PushNotificationManager
import com.bferrari.stonechallenge.utils.WorkerDispatcher
import com.bferrari.usecases.injection.useCaseModules
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class StoneChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)
        PushNotificationManager.init(this)
        startKoin(this, listOf(useCaseModules, apiModule, appModule, dataModule, frameworkModule))
        WorkerDispatcher.dispatch(this)
    }

    override fun onTerminate() {
        WorkerDispatcher.clearDisposables()
        super.onTerminate()
    }

}
