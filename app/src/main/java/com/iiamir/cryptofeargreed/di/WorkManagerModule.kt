package com.iiamir.cryptofeargreed.di

import com.iiamir.cryptofeargreed.presentation.widget.worker.FGWidgetWorker
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val workManagerModule = module {
    single <FGWidgetWorker> {
        FGWidgetWorker(
            appContext = androidContext(),
            repository = get(),
            workerParameters = it.get()
        )
    }
}