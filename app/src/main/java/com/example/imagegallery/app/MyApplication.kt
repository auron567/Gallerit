package com.example.imagegallery.app

import android.app.Application
import com.example.imagegallery.BuildConfig
import com.example.imagegallery.di.appModule
import com.example.imagegallery.di.databaseModule
import com.example.imagegallery.di.networkModule
import com.example.imagegallery.di.repositoryModule
import com.example.imagegallery.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
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
            androidLogger(Level.ERROR)
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
