package com.iiamir.cryptofeargreed.presentation.screens.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iiamir.cryptofeargreed.presentation.screens.components.PullRefreshIndicator
import com.iiamir.cryptofeargreed.presentation.screens.home.components.content.HomeLazyListContent
import com.iiamir.cryptofeargreed.presentation.screens.home.components.content.LoadingAndErrorContent
import com.iiamir.cryptofeargreed.presentation.screens.home.components.custom_views.ShowAboutDialog
import com.iiamir.cryptofeargreed.presentation.screens.home.components.topbar.TopAppBar
import com.iiamir.cryptofeargreed.presentation.widget.worker.startUpdateUiWorker
import com.iiamir.cryptofeargreed.utils.Constants
import com.iiamir.cryptofeargreed.utils.MenuActions
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    context: Context,
    homeScreenViewModel: HomeScreenViewModel,
    isInDarkMode: Boolean,
    onChangeThemeClicked: () -> Unit,
    onExitAppClicked: () -> Unit,
    onLastYearIndexShowClicked: () -> Unit,
    onViewAllFGDataClicked: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val isRefreshing = homeScreenViewModel.getAllHomeScreenDataState.value.isRefreshing
    val lastYearIndexIsGone = homeScreenViewModel.lastYearIndexIsGone.collectAsState().value
    var dialogState by remember {
        mutableStateOf(false)
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = homeScreenViewModel::callAllViewModels
    )

    LaunchedEffect(key1 = true) {
        homeScreenViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HomeScreenUIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                else -> {}
            }
        }
    }

    if (dialogState) {
        ShowAboutDialog { dismiss, showBrowser ->
            dialogState = dismiss
            if (showBrowser) {
                MenuActions.showBrowserAction(context)
            }
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(
                    snackbarData = data,
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary
                )
            }
        },
        topBar = {
            TopAppBar(isInDarkMode = isInDarkMode,
                onChangeThemeClicked = onChangeThemeClicked,
                onItemClicked = { menuOption ->
                    when (menuOption.text) {
                        Constants.ABOUT -> {
                            dialogState = true
                        }
                        Constants.SEND_FEEDBACK -> {
                            MenuActions.sendFeedbackAction(context)
                        }
                        Constants.RATE_APP -> {
                            MenuActions.rateAppAction(context)
                        }
                        Constants.EXIT_APP -> {
                            onExitAppClicked()
                        }
                    }
                },
                onShowLastYearIndexClicked = {
                    homeScreenViewModel.saveLastYearIndexIsGoneViewModel(!lastYearIndexIsGone)
                    onLastYearIndexShowClicked()
                }
            )
        }
    ) {
        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {

            LoadingAndErrorContent(homeScreenViewModel.getAllHomeScreenDataState.value) {
                homeScreenViewModel.callAllViewModels()
            }

            HomeLazyListContent(
                homeScreenDataState = homeScreenViewModel.getAllHomeScreenDataState.value,
                homeScreenClassificationDataState = homeScreenViewModel.getHomeScreenClassificationDataState.collectAsState().value,
                lastYearIndexIsGone = lastYearIndexIsGone,
                isInDarkMode = isInDarkMode,
                onChangeChartAction = { indexChart ->
                    homeScreenViewModel.onEvent(indexChart)
                },
                timeUntilUpdateExpiredAction = {
                    homeScreenViewModel.callAllViewModels()
                    context.startUpdateUiWorker()
                },
                onViewAllFGDataClicked = onViewAllFGDataClicked,
                onItemCategoryClicked = {
                    homeScreenViewModel.onEvent(
                        HomeScreenUIEvent.OnPieGraphCategoryChange(
                            it
                        )
                    )
                }
            )

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                pullRefreshState = pullRefreshState
            )
        }
    }
}