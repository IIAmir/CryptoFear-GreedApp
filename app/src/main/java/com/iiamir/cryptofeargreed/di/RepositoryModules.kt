package com.iiamir.cryptofeargreed.di

import com.iiamir.cryptofeargreed.data.repository.DataStoreOperationsImpl
import com.iiamir.cryptofeargreed.data.repository.FGDataRepositoryImpl
import com.iiamir.cryptofeargreed.domain.repository.DataStoreOperations
import com.iiamir.cryptofeargreed.domain.repository.FGDataRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModules = module {
    single<DataStoreOperations> {
        DataStoreOperationsImpl(
            context = androidContext()
        )
    }
    single<FGDataRepository> {
        FGDataRepositoryImpl(
            fgApiService = get(),
            fgDataDao = get()
        )
    }
}