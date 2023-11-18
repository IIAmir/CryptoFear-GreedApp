package com.iiamir.cryptofeargreed.domain.repository

import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.utils.Constants.GET_ALL_FEAR_GREED
import com.iiamir.cryptofeargreed.utils.Constants.GET_NEXT_UPDATE_TIME_STAMP
import com.iiamir.cryptofeargreed.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FGDataRepository {

    fun getAllFGDataRepo(limit: Int = GET_ALL_FEAR_GREED): Flow<Resource<List<FGDataModel>>>

    fun getFilteredFGDataRepo(date: String): Flow<Resource<List<Pair<String, Int>>>>

    fun getNextUpdateTimeStampRepo(limit: Int = GET_NEXT_UPDATE_TIME_STAMP): Flow<Resource<FGDataModel>>

    // Version 2

    fun searchFGDataRepo(
        fetchFromRemote: Boolean,
        date: String,
        limit: Int = GET_ALL_FEAR_GREED
    ): Flow<Resource<List<FGDataModel>>>

}