package com.example.cryptofeargreed.domain.repository

import com.example.cryptofeargreed.data.remote.dto.FGDataModel
import com.example.cryptofeargreed.utils.Constants
import com.example.cryptofeargreed.utils.Constants.GET_ALL_FEAR_GREED
import com.example.cryptofeargreed.utils.Constants.GET_A_MONTH_FEAR_GREED
import com.example.cryptofeargreed.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FGDataRepository {

    suspend fun getCustomFGDataRepo(limit: Int = GET_A_MONTH_FEAR_GREED): Flow<Resource<List<FGDataModel>>>

    suspend fun getAllFGDataRepo(limit: Int = GET_ALL_FEAR_GREED): Flow<Resource<List<FGDataModel>>>

}