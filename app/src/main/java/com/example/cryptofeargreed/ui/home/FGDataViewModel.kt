package com.example.cryptofeargreed.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptofeargreed.domain.repository.FGDataRepository
import com.example.cryptofeargreed.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FGDataViewModel @Inject constructor(
    private val fgDataRepository: FGDataRepository,
) : ViewModel() {

    private val _getAMonthFGDataState = mutableStateOf(FGDataState())
    val getAMonthFGDataState: State<FGDataState> = _getAMonthFGDataState
    private val _getAllFGDataState = mutableStateOf(FGDataState())
    val getAllFGDataState: State<FGDataState> = _getAllFGDataState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getAMonthFGDataViewModel()
        getAllFGDataViewModel()
    }

    fun getAMonthFGDataViewModel() {
        viewModelScope.launch {
            fgDataRepository.getCustomFGDataRepo()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _getAMonthFGDataState.value = getAMonthFGDataState.value.copy(
                                fgDataState = result.data ?: emptyList(),
                                isLoading = false,
                                error = null,
                            )
                        }
                        is Resource.Loading -> {
                            _getAMonthFGDataState.value = getAMonthFGDataState.value.copy(
                                fgDataState = result.data ?: emptyList(),
                                isLoading = true,
                                error = null,
                            )
                        }
                        is Resource.Error -> {
                            _getAMonthFGDataState.value = getAMonthFGDataState.value.copy(
                                fgDataState = result.data ?: emptyList(),
                                isLoading = false,
                                error = result.message,
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        }
                    }
                }.launchIn(this)
        }
    }

    fun getAllFGDataViewModel() {
        viewModelScope.launch {
            fgDataRepository.getAllFGDataRepo()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _getAllFGDataState.value = getAllFGDataState.value.copy(
                                fgDataState = result.data ?: emptyList(),
                                isLoading = false,
                                error = null,
                            )
                        }
                        is Resource.Loading -> {
                            _getAllFGDataState.value = getAllFGDataState.value.copy(
                                fgDataState = result.data ?: emptyList(),
                                isLoading = true,
                                error = null,
                            )
                        }
                        is Resource.Error -> {
                            _getAllFGDataState.value = getAllFGDataState.value.copy(
                                fgDataState = result.data ?: emptyList(),
                                isLoading = false,
                                error = result.message,
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        }
                    }
                }.launchIn(this)
        }
    }
}