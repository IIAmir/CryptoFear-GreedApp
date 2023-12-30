package com.iiamir.cryptofeargreed.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiamir.cryptofeargreed.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val dataStoreOperations: DataStoreOperations
) : ViewModel() {

    private val _isInDarkMode = MutableStateFlow(true)
    val isInDarkMode: StateFlow<Boolean> = _isInDarkMode

    init {
        viewModelScope.launch {
            dataStoreOperations
                .readAppIsInDarkModeByUserState()
                .stateIn(viewModelScope)
                .collect { updatedValue ->
                    _isInDarkMode.value = updatedValue
                }
        }
    }

}