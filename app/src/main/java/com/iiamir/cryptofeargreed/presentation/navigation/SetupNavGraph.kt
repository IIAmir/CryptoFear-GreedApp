package com.iiamir.cryptofeargreed.presentation.navigation

import android.content.Context
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreen
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenViewModel
import com.iiamir.cryptofeargreed.presentation.screens.search.SearchScreen
import com.iiamir.cryptofeargreed.presentation.screens.search.SearchScreenUIEvent
import com.iiamir.cryptofeargreed.presentation.screens.search.SearchScreenViewModel
import com.iiamir.cryptofeargreed.presentation.screens.splash.SplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    context: Context,
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    searchScreenViewModel: SearchScreenViewModel,
    isInDarkMode: Boolean,
    onChangeThemeClicked: () -> Unit,
    onLastYearIndexShowClicked: () -> Unit,
    onExitAppClicked: () -> Unit
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(
                isInDarkMode = isInDarkMode,
                showHomeScreenAction = {
                    navHostController.popBackStack()
                    navHostController.navigate(Screen.Home.route)
                }
            )
        }
        composable(
            route = Screen.Home.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            HomeScreen(
                context = context,
                homeScreenViewModel = homeScreenViewModel,
                isInDarkMode = isInDarkMode,
                onChangeThemeClicked = onChangeThemeClicked,
                onExitAppClicked = onExitAppClicked,
                onLastYearIndexShowClicked = onLastYearIndexShowClicked,
                onViewAllFGDataClicked = {
                    searchScreenViewModel.onEvent(SearchScreenUIEvent.Refresh)
                    navHostController.navigate(Screen.Search.route)
                }
            )
        }
        composable(
            route = Screen.Search.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            }
        ) {
            SearchScreen(
                searchScreenViewModel = searchScreenViewModel,
                onBackIconClicked = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}