package com.iiamir.cryptofeargreed

import android.app.Application
import com.iiamir.cryptofeargreed.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CryptoFGApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@CryptoFGApplication)
            workManagerFactory()
            modules(
                networkModule,
                databaseModule,
                repositoryModules,
                viewModelModules,
                workManagerModule
            )
        }
    }
}