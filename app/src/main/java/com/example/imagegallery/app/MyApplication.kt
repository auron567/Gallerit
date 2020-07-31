package com.example.imagegallery.app

import android.app.Application
import com.example.imagegallery.BuildConfig
import com.example.imagegallery.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupTimber()
    }

    /**
     * Koin configuration.
     */
    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                appModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }

    /**
     * Timber configuration.
     */
    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}