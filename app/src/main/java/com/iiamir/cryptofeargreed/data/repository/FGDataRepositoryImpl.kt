package com.iiamir.cryptofeargreed.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.iiamir.cryptofeargreed.data.local.FGDataDao
import com.iiamir.cryptofeargreed.data.remote.FGApiService
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.data.remote.dto.FGDto
import com.iiamir.cryptofeargreed.domain.repository.FGDataRepository
import com.iiamir.cryptofeargreed.utils.Constants
import com.iiamir.cryptofeargreed.utils.Constants.ALL
import com.iiamir.cryptofeargreed.utils.Constants.GET_TODAY_FG_BY_INDEX
import com.iiamir.cryptofeargreed.utils.Constants.SHOW_NETWORK_ERROR_MESSAGE
import com.iiamir.cryptofeargreed.utils.Constants.SHOW_UNKNOWN_ERROR
import com.iiamir.cryptofeargreed.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class FGDataRepositoryImpl(
    private val fgApiService: FGApiService,
    private val fgDataDao: FGDataDao,
) : FGDataRepository {

    override fun getAllFGDataRepo(limit: Int): Flow<Resource<List<FGDataModel>>> = flow {
        emit(Resource.Loading())
        val dataDatabase = fgDataDao.getFGData().map { it.toFGDataModel() }
        emit(Resource.Loading(data = dataDatabase))

        try {
            val remoteData = fgApiService.getAllFearNGreedData(limit = limit)
            deleteAndInsertData(
                fgDataDao = fgDataDao,
                insertData = remoteData,
            )
        } catch (e: HttpException) {
            emitHttpException(e.message(), dataDatabase)
        } catch (e: IOException) {
            emitIOException(data = dataDatabase)
        }
        val newDataDatabase = fgDataDao.getFGData().map { it.toFGDataModel() }
        emit(Resource.Success(data = newDataDatabase))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getFilteredFGDataRepo(
        date: String
    ): Flow<Resource<List<Pair<String, Int>>>> = flow {
        val classificationData: MutableList<Pair<String, Int>> = mutableListOf()
        val classificationCategoryList = listOf(
            Constants.EXTREME_FEAR,
            Constants.FEAR,
            Constants.NEUTRAL,
            Constants.GREED,
            Constants.EXTREME_GREED,
        )
        try {
            if (fgDataDao.getFGData().isNotEmpty()) {
                classificationCategoryList.forEachIndexed { index, data ->
                    classificationData.add(
                        index = index,
                        Pair(
                            data,
                            fgDataDao.searchFGData(
                                if (date == ALL) "" else date, data
                            ).size
                        )
                    )
                }
            }
        } catch (e: HttpException) {
            emitHttpException(error = e.message(), data = classificationData)
        } catch (e: IOException) {
            emitIOException(data = classificationData)
        }
        emit(Resource.Success(data = classificationData))
    }

    override fun getNextUpdateTimeStampRepo(limit: Int): Flow<Resource<FGDataModel>> = flow {
        try {
            val remoteData = fgApiService.getAllFearNGreedData(limit = limit)
            emit(Resource.Success(data = remoteData.data[GET_TODAY_FG_BY_INDEX]))
        } catch (e: HttpException) {
            emitHttpException(e.message())
        } catch (e: IOException) {
            emitIOException()
        }
    }

    override fun searchFGDataRepo(
        fetchFromRemote: Boolean,
        date: String,
        limit: Int
    ): Flow<Resource<List<FGDataModel>>> = flow {
        emit(Resource.Loading(isLoading = true))
        val localDatabase = fgDataDao.searchFGData(date, "")
        emit(Resource.Success(
            data = localDatabase.map { it.toFGDataModel() }
        ))
        val isDatabaseEmpty = localDatabase.isEmpty() && date.isBlank()
        val shouldJustLoadFromCatch = !isDatabaseEmpty && !fetchFromRemote
        if (shouldJustLoadFromCatch) {
            emit(Resource.Loading(isLoading = false))
            return@flow
        }

        val remoteData = try {
            fgApiService.getAllFearNGreedData(limit = limit)
        } catch (e: IOException) {
            emitIOException()
            null
        } catch (e: HttpException) {
            emitHttpException(e.message())
            null
        }

        remoteData?.let {
            deleteAndInsertData(
                fgDataDao = fgDataDao,
                insertData = remoteData,
            )
            emit(Resource.Loading(isLoading = false))
        }
    }

    private suspend fun deleteAndInsertData(
        fgDataDao: FGDataDao,
        insertData: FGDto,
    ) {
        // Prevent To Calculating Data (When Inserting In Database)
        if (fgDataDao.getFGData().size != insertData.data.size) {
            fgDataDao.deleteFGData()
            fgDataDao.insertFGData(insertData.data.map { it.toFGEntityAndInsertConvertedData() })
        }
    }

    private fun emitHttpException(error: String, data: Any? = null) = flow {
        emit(
            Resource.Error(
                message = SHOW_UNKNOWN_ERROR + error,
                data = data
            )
        )
    }

    private fun emitIOException(data: Any? = null) = flow {
        emit(
            Resource.Error(
                message = SHOW_NETWORK_ERROR_MESSAGE,
                data = data
            )
        )
    }

}