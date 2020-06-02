package com.bferrari.stonechallenge

import android.app.Application
import androidx.test.espresso.idling.CountingIdlingResource
import com.bferrari.data.injection.apiModule
import com.bferrari.data.injection.dataModule
import com.bferrari.framework.injection.frameworkModule
import com.bferrari.stonechallenge.injection.appModule
import com.bferrari.stonechallenge.utils.PushNotificationManager
import com.bferrari.stonechallenge.utils.WorkerDispatcher
import com.bferrari.usecases.injection.useCaseModules
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin


class StoneChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        PushNotificationManager.init(this)
        startKoin(this, listOf(useCaseModules, apiModule, appModule, dataModule, frameworkModule))
        WorkerDispatcher.dispatch(this)
    }
}
