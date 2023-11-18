package com.iiamir.cryptofeargreed.presentation

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.iiamir.cryptofeargreed.presentation.navigation.SetupNavGraph
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenViewModel
import com.iiamir.cryptofeargreed.presentation.screens.search.SearchScreenViewModel
import com.iiamir.cryptofeargreed.presentation.screens.splash.SplashScreenViewModel
import com.iiamir.cryptofeargreed.presentation.theme.CryptoFearGreedTheme
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val homeScreenViewModel: HomeScreenViewModel by inject()
    private val splashScreenViewModel: SplashScreenViewModel by inject()
    private val searchScreenViewModel: SearchScreenViewModel by inject()

    private var isInDarkModeByUserState: Boolean = false

    private lateinit var navHostController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(Configuration())

        setContent {
            isInDarkModeByUserState = splashScreenViewModel.isInDarkMode.collectAsState().value

            var isInDarkModeByUser by remember {
                mutableStateOf(isInDarkModeByUserState)
            }
            navHostController = rememberAnimatedNavController()

            CryptoFearGreedTheme(darkThemeByUser = isInDarkModeByUser) {
                Surface(
                    contentColor = MaterialTheme.colors.primary,
                    color = MaterialTheme.colors.primary
                ) {
                    SetupNavGraph(
                        context = this,
                        navHostController = navHostController,
                        homeScreenViewModel = homeScreenViewModel,
                        searchScreenViewModel = searchScreenViewModel,
                        isInDarkMode = isInDarkModeByUser,
                        onChangeThemeClicked = { // Restart Activity And Apply Theme
                            isInDarkModeByUser = !isInDarkModeByUser
                            homeScreenViewModel.saveAppIsInDarkModeByUserViewModel(isInDarkModeByUser)
                        },
                        onLastYearIndexShowClicked = ::restartActivity,
                        onExitAppClicked = ::finish // On Exit App Clicked => Close Activity
                    )
                }
            }
        }
    }

    // prevent to change font size by system
    private fun adjustFontScale(configuration: Configuration) {
        if (configuration.fontScale != 1f) {
            resources.configuration.fontScale = 1f
        }
    }

    private fun restartActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

}