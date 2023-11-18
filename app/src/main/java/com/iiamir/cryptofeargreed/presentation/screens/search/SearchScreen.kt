package com.iiamir.cryptofeargreed.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.iiamir.cryptofeargreed.presentation.screens.components.FearNGreedItem
import com.iiamir.cryptofeargreed.presentation.screens.components.PullRefreshIndicator
import com.iiamir.cryptofeargreed.presentation.screens.search.components.EmptyScreen
import com.iiamir.cryptofeargreed.presentation.screens.search.components.FloatingActionButton
import com.iiamir.cryptofeargreed.presentation.screens.search.components.SearchWidget
import com.iiamir.cryptofeargreed.presentation.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "FrequentlyChangedStateReadInComposition")
@Composable
fun SearchScreen(
    searchScreenViewModel: SearchScreenViewModel,
    onBackIconClicked: () -> Unit,
) {
    val searchState = searchScreenViewModel.getSearchResultState.value
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isRefreshing = searchState.isRefreshing
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { searchScreenViewModel.onEvent(SearchScreenUIEvent.Refresh) }
    )

    Scaffold(
        topBar = {
            SearchWidget(
                textValue = searchScreenViewModel.getSearchResultState.value.searchQuery,
                onValueChange = {
                    searchScreenViewModel.onEvent(
                        SearchScreenUIEvent.OnSearchQueryChange(
                            it
                        )
                    )
                },
                onBackIconClicked = onBackIconClicked,
                onCloseIconClicked = { searchScreenViewModel.onEvent(SearchScreenUIEvent.OnCloseButtonClicked) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                floatingActionButtonVisibility = lazyListState.firstVisibleItemIndex > 10 && !lazyListState.isScrollInProgress,
                floatingActionButtonClicked = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(
                            0,
                            0
                        )
                    }
                }
            )
        },
        contentColor = MaterialTheme.colors.primary,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(
            modifier = Modifier.pullRefresh(pullRefreshState),
            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.height(TEN))

            if (searchState.searchResultList.isEmpty()) {
                EmptyScreen()
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
                        items(searchState.searchResultList) { result ->
                            FearNGreedItem(fgDataModel = result)
                        }
                        item {
                            Spacer(modifier = Modifier.height(SEVENTY))
                        }
                    }
                    AnimatedVisibility(
                        visible = searchState.searchResultList.size > 10,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(SEVENTY)
                                .align(Alignment.BottomCenter)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.Transparent,
                                            MaterialTheme.colors.secondary,
                                        ),
                                        startY = 30f
                                    )
                                )
                        )
                    }
                }
            }

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                pullRefreshState = pullRefreshState
            )
        }
    }
}