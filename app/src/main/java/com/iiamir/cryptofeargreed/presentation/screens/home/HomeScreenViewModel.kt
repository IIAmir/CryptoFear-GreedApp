package com.iiamir.cryptofeargreed.presentation.screens.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiamir.cryptofeargreed.data.util.calculatingPieGraphCategory
import com.iiamir.cryptofeargreed.domain.repository.DataStoreOperations
import com.iiamir.cryptofeargreed.domain.repository.FGDataRepository
import com.iiamir.cryptofeargreed.utils.IndexChart
import com.iiamir.cryptofeargreed.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val fgDataRepository: FGDataRepository,
    private val dataStoreOperations: DataStoreOperations
) : ViewModel() {

    private val _getAllHomeScreenDataState = mutableStateOf(HomeScreenDataState())
    val getAllHomeScreenDataState: State<HomeScreenDataState> = _getAllHomeScreenDataState

    private val _getHomeScreenClassificationDataState =
        MutableStateFlow(HomeScreenClassificationDataState())
    val getHomeScreenClassificationDataState: StateFlow<HomeScreenClassificationDataState> =
        _getHomeScreenClassificationDataState.asStateFlow()

    private val _lastYearIndexIsGone = MutableStateFlow(true)
    val lastYearIndexIsGone: StateFlow<Boolean> = _lastYearIndexIsGone

    private val _eventFlow = MutableSharedFlow<HomeScreenUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getAllFGDataViewModel()
        getNextUpdateTimeStampViewModel()
        viewModelScope.launch {
            _lastYearIndexIsGone.value =
                dataStoreOperations
                    .readLastYearIndexValueIsGoneState()
                    .stateIn(viewModelScope)
                    .value
        }
    }

    fun onEvent(event: HomeScreenUIEvent) {
        when (event) {
            is HomeScreenUIEvent.Refresh -> {
                callAllViewModels()
            }
            is HomeScreenUIEvent.ShowLineGraph -> {
                _getAllHomeScreenDataState.value = getAllHomeScreenDataState.value.copy(
                    indexChart = IndexChart.LINE
                )
            }
            is HomeScreenUIEvent.ShowBarGraph -> {
                _getAllHomeScreenDataState.value = getAllHomeScreenDataState.value.copy(
                    indexChart = IndexChart.BAR
                )
            }
            is HomeScreenUIEvent.OnPieGraphCategoryChange -> {
                getFGClassificationByYearViewModel(event.year)
            }
            else -> {}
        }
    }

    fun callAllViewModels() {
        getAllFGDataViewModel()
        getNextUpdateTimeStampViewModel()
        getFGClassificationByYearViewModel(year = getHomeScreenClassificationDataState.value.selectedClassificationDataCategory)
    }

    fun saveAppIsInDarkModeByUserViewModel(isInDarkMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreOperations.saveAppIsInDarkModeByUserState(isInDarkMode)
        }
    }

    fun saveLastYearIndexIsGoneViewModel(isGone: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreOperations.saveLastYearIndexValueIsGoneState(isGone)
        }
    }

    private fun getAllFGDataViewModel() {
        viewModelScope.launch {
            fgDataRepository.getAllFGDataRepo().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _getAllHomeScreenDataState.value = getAllHomeScreenDataState.value.copy(
                            fgDataModelList = result.data ?: emptyList(),
                            isLoading = false,
                            error = null,
                        )
                        getFGClassificationByYearViewModel(year = getHomeScreenClassificationDataState.value.selectedClassificationDataCategory)
                    }
                    is Resource.Loading -> {
                        _getAllHomeScreenDataState.value = getAllHomeScreenDataState.value.copy(
                            fgDataModelList = result.data ?: emptyList(),
                            isLoading = true,
                            error = null,
                        )
                    }
                    is Resource.Error -> {
                        _getAllHomeScreenDataState.value = getAllHomeScreenDataState.value.copy(
                            fgDataModelList = result.data ?: emptyList(),
                            isLoading = false,
                            error = result.message,
                        )
                        _eventFlow.emit(
                            HomeScreenUIEvent.ShowSnackBar(
                                result.message ?: "Unknown Error"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getFGClassificationByYearViewModel(year: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fgDataRepository.getFilteredFGDataRepo(date = year)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            Log.d("RESULT CLASSIFICATION", result.data.toString())
                            _getHomeScreenClassificationDataState.update {
                                getHomeScreenClassificationDataState.value.copy(
                                    selectedClassificationDataCategory = year,
                                    classificationDataCategory = calculatingPieGraphCategory(),
                                    classificationDataList = result.data ?: emptyList()
                                )
                            }
                        }
                        else -> {}
                    }
                }.launchIn(this)
        }
    }

    private fun getNextUpdateTimeStampViewModel() {
        viewModelScope.launch {
            fgDataRepository.getNextUpdateTimeStampRepo()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _getAllHomeScreenDataState.value =
                                getAllHomeScreenDataState.value.copy(
                                    timeUntilUpdate = result.data!!.timeUntilUpdate
                                )
                        }
                        is Resource.Error -> {
                            _getAllHomeScreenDataState.value =
                                getAllHomeScreenDataState.value.copy(
                                    timeUntilUpdate = null,
                                )
                        }
                        else -> {}
                    }
                }.launchIn(this)
        }
    }

}