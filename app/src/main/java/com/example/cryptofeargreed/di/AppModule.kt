package com.example.cryptofeargreed.di

import android.app.Application
import androidx.room.Room
import com.example.cryptofeargreed.data.local.FGDataDatabase
import com.example.cryptofeargreed.data.remote.FGApiService
import com.example.cryptofeargreed.data.repository.FGDataRepositoryImpl
import com.example.cryptofeargreed.domain.repository.FGDataRepository
import com.example.cryptofeargreed.utils.Constants.BASE_URL
import com.example.cryptofeargreed.utils.Constants.DATABASE_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFGDataRepository(
        fgApiService: FGApiService,
        database: FGDataDatabase,
    ): FGDataRepository {
        return FGDataRepositoryImpl(fgApiService, database.fgDataDao)
    }

    @Provides
    @Singleton
    fun provideCryptoFGDataDatabase(
        application: Application,
    ): FGDataDatabase {
        return Room.databaseBuilder(
            application, FGDataDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCryptoFGApi(): FGApiService {
        val gsonBuilder = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()
            .create(FGApiService::class.java)
    }

}