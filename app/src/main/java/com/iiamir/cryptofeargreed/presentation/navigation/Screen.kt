package com.iiamir.cryptofeargreed.presentation.navigation

import com.iiamir.cryptofeargreed.utils.Constants.HOME_SCREEN_ROUTE
import com.iiamir.cryptofeargreed.utils.Constants.SEARCH_SCREEN_ROUTE
import com.iiamir.cryptofeargreed.utils.Constants.SPLASH_SCREEN_ROUTE

sealed class Screen(val route: String) {

    object Splash : Screen(SPLASH_SCREEN_ROUTE)

    object Home : Screen(HOME_SCREEN_ROUTE)

    object Search : Screen(SEARCH_SCREEN_ROUTE)

}
