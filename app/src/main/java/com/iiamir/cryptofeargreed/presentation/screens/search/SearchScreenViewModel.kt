package com.iiamir.cryptofeargreed.presentation.screens.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiamir.cryptofeargreed.domain.repository.FGDataRepository
import com.iiamir.cryptofeargreed.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val fgDataRepository: FGDataRepository
) : ViewModel() {

    private var _getSearchResultState = mutableStateOf(SearchScreenDataState())
    var getSearchResultState: State<SearchScreenDataState> = _getSearchResultState

    private var searchJob: Job? = null

    fun onEvent(event: SearchScreenUIEvent) {
        when (event) {
            is SearchScreenUIEvent.OnSearchQueryChange -> {
                _getSearchResultState.value = getSearchResultState.value.copy(
                    searchQuery = event.query
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getSearchResultViewModel()
                }
            }
            is SearchScreenUIEvent.OnCloseButtonClicked -> {
                _getSearchResultState.value = getSearchResultState.value.copy(
                    searchQuery = ""
                )
                getSearchResultViewModel()
            }
            is SearchScreenUIEvent.Refresh -> {
                getSearchResultViewModel(fetchFromRemote = true)
            }
        }
    }

    private fun getSearchResultViewModel(
        fetchFromRemote: Boolean = false,
        query: String = _getSearchResultState.value.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            fgDataRepository.searchFGDataRepo(
                fetchFromRemote = fetchFromRemote,
                date = query
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _getSearchResultState.value = getSearchResultState.value.copy(
                            searchResultList = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Loading -> {
                        _getSearchResultState.value = getSearchResultState.value.copy(
                            isLoading = result.isLoading
                        )
                    }
                    is Resource.Error -> {
                        Log.d("ERROR ==> ", "ERROR OCCURRED")
                    }
                }
            }
        }
    }

}