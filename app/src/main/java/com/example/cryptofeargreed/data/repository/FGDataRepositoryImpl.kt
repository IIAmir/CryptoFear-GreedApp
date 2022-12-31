package com.example.cryptofeargreed.data.repository

import com.example.cryptofeargreed.data.local.FGDataDao
import com.example.cryptofeargreed.data.remote.FGApiService
import com.example.cryptofeargreed.data.remote.dto.FGDataModel
import com.example.cryptofeargreed.domain.repository.FGDataRepository
import com.example.cryptofeargreed.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class FGDataRepositoryImpl(
    private val fgApiService: FGApiService,
    private val fgDataDao: FGDataDao,
) : FGDataRepository {

    override suspend fun getCustomFGDataRepo(limit: Int): Flow<Resource<List<FGDataModel>>> = flow {
        emit(Resource.Loading())
        val dataDatabase = fgDataDao.getFGData().map { it.toFGDataModel() }
        emit(Resource.Loading(data = dataDatabase))

        try {
            val remoteData = fgApiService.getFearAndGreedByDay(limit = limit).body()!!
            fgDataDao.deleteFGData()
            fgDataDao.insertFGData(remoteData.data.map { it.toFGEntity() })

        } catch (e: HttpException) {
            Resource.Error(
                message = "Oops, something went wrong! ${e.message}",
                data = dataDatabase
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = dataDatabase
                )
            )
        }
        val newDataDatabase = fgDataDao.getFGData().map { it.toFGDataModel() }
        emit(Resource.Success(data = newDataDatabase))
    }

    override suspend fun getAllFGDataRepo(limit: Int): Flow<Resource<List<FGDataModel>>> = flow {
        emit(Resource.Loading())
        val dataDatabase = fgDataDao.getFGData().map { it.toFGDataModel() }
        emit(Resource.Loading(data = dataDatabase))

        try {
            val remoteData = fgApiService.getFearAndGreedByDay(limit = limit).body()!!
            fgDataDao.deleteFGData()
            fgDataDao.insertFGData(remoteData.data.map { it.toFGEntity() })

        } catch (e: HttpException) {
            Resource.Error(
                message = "Oops, something went wrong! ${e.message}",
                data = dataDatabase
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = dataDatabase
                )
            )
        }
        val newDataDatabase = fgDataDao.getFGData().map { it.toFGDataModel() }
        emit(Resource.Success(data = newDataDatabase))
    }

}