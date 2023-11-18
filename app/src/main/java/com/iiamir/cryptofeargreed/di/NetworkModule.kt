package com.iiamir.cryptofeargreed.di

import com.google.gson.GsonBuilder
import com.iiamir.cryptofeargreed.data.remote.FGApiService
import com.iiamir.cryptofeargreed.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<FGApiService> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .create()
                    )
            )
            .client(
                OkHttpClient().newBuilder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(FGApiService::class.java)
    }
}