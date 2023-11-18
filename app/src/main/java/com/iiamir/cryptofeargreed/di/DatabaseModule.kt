package com.iiamir.cryptofeargreed.di

import androidx.room.Room
import com.iiamir.cryptofeargreed.data.local.FGDataDao
import com.iiamir.cryptofeargreed.data.local.FGDataDatabase
import com.iiamir.cryptofeargreed.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<FGDataDao> {
        Room.databaseBuilder(
            androidContext(),
            FGDataDatabase::class.java,
            Constants.DATABASE_NAME
        ).build().fgDataDao
    }
}