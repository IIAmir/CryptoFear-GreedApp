package com.iiamir.cryptofeargreed.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveAppIsInDarkModeByUserState(isInDarkMode: Boolean)

    fun readAppIsInDarkModeByUserState(): Flow<Boolean>

    suspend fun saveLastYearIndexValueIsGoneState(isGone: Boolean)

    fun readLastYearIndexValueIsGoneState(): Flow<Boolean>

}