package com.iiamir.cryptofeargreed.presentation.screens.home

import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.utils.IndexChart

data class HomeScreenDataState(
    val fgDataModelList: List<FGDataModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = "",
    val timeUntilUpdate: Int? = null,
    val indexChart: IndexChart = IndexChart.BAR,
    val isRefreshing: Boolean = false
)
