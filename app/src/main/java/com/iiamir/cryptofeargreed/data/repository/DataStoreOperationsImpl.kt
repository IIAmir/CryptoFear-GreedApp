package com.iiamir.cryptofeargreed.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.iiamir.cryptofeargreed.data.util.PreferencesKey
import com.iiamir.cryptofeargreed.domain.repository.DataStoreOperations
import com.iiamir.cryptofeargreed.utils.Constants.PREFERENCES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES)

class DataStoreOperationsImpl(
    context: Context
) : DataStoreOperations {

    private val dataStore = context.dataStore

    override suspend fun saveAppIsInDarkModeByUserState(isInDarkMode: Boolean) {
        savePreferencesKeys(key = PreferencesKey.isInDarkModeKey, value = isInDarkMode)
    }

    override fun readAppIsInDarkModeByUserState(): Flow<Boolean> {
        return readPreferencesKeysValue(PreferencesKey.isInDarkModeKey)
    }

    override suspend fun saveLastYearIndexValueIsGoneState(isGone: Boolean) {
        savePreferencesKeys(key = PreferencesKey.lastYearIndexIsGone, value = isGone)
    }

    override fun readLastYearIndexValueIsGoneState(): Flow<Boolean> {
        return readPreferencesKeysValue(PreferencesKey.lastYearIndexIsGone)
    }


    private suspend fun savePreferencesKeys(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { pref ->
            pref[key] = value
        }
    }

    private fun readPreferencesKeysValue(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { pref ->
                val prefValue = pref[key] ?: true
                if (key.name == PreferencesKey.isInDarkModeKey.name) {
                    Log.d(
                        "PREF VALUE ==> ",
                        prefValue.toString()
                    )
                }
                prefValue
            }
    }

}