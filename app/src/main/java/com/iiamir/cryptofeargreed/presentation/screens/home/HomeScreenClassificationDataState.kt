package com.iiamir.cryptofeargreed.presentation.screens.home

import com.iiamir.cryptofeargreed.utils.Constants.ALL

data class HomeScreenClassificationDataState(
    val selectedClassificationDataCategory: String = ALL,
    val classificationDataCategory: List<String> = emptyList(),
    val classificationDataList: List<Pair<String, Int>> = emptyList()
)