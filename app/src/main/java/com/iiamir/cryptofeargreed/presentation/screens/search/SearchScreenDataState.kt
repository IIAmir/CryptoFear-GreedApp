package com.iiamir.cryptofeargreed.presentation.screens.search

import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel

data class SearchScreenDataState(
    val searchResultList: List<FGDataModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val isRefreshing: Boolean = false
)
