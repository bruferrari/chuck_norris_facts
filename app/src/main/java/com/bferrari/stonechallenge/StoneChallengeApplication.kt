package com.bferrari.stonechallenge

import android.app.Application
import com.bferrari.data.injection.apiModule
import com.bferrari.data.injection.dataModule
import com.bferrari.framework.injection.frameworkModule
import com.bferrari.stonechallenge.injection.appModule
import com.bferrari.usecases.injection.useCaseModules
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin


class StoneChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        startKoin(this, listOf(useCaseModules, apiModule, appModule, dataModule, frameworkModule))
    }
}