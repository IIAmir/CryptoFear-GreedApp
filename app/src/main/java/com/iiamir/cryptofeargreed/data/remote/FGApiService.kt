package com.iiamir.cryptofeargreed.data.remote
import com.iiamir.cryptofeargreed.data.remote.dto.FGDto
import com.iiamir.cryptofeargreed.utils.Constants.REQUEST_QUERY
import retrofit2.http.GET
import retrofit2.http.Query

interface FGApiService {

    @GET("/fng/")
    suspend fun getAllFearNGreedData(@Query(REQUEST_QUERY) limit: Int): FGDto

}