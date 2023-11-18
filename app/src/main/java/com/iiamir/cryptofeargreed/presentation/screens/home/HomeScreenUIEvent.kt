package com.iiamir.cryptofeargreed.presentation.screens.home

sealed class HomeScreenUIEvent {

    data class OnPieGraphCategoryChange(val year: String) : HomeScreenUIEvent()

    class ShowSnackBar(val message: String) : HomeScreenUIEvent()

    object ShowLineGraph : HomeScreenUIEvent()

    object ShowBarGraph : HomeScreenUIEvent()

    object Refresh : HomeScreenUIEvent()

}
