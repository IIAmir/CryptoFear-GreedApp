package com.iiamir.cryptofeargreed.presentation.screens.search

sealed class SearchScreenUIEvent {

    data class OnSearchQueryChange(val query: String) : SearchScreenUIEvent()

    object OnCloseButtonClicked : SearchScreenUIEvent()

    object Refresh : SearchScreenUIEvent()

}
