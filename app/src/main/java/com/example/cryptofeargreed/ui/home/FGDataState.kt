package com.example.cryptofeargreed.ui.home

import com.example.cryptofeargreed.data.remote.dto.FGDataModel

data class FGDataState(
    val fgDataState: List<FGDataModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
