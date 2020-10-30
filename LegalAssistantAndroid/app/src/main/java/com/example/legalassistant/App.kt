package com.example.legalassistant

import android.app.Application
import com.example.legalassistant.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}