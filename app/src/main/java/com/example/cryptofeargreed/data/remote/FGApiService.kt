package com.example.cryptofeargreed.data.remote

import com.example.cryptofeargreed.data.remote.dto.FGDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FGApiService {

    @GET("/fng/")
    suspend fun getFearAndGreedByDay(@Query("limit") limit: Int): Response<FGDto>

}