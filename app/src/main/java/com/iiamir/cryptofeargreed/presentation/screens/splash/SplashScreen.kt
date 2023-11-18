package com.iiamir.cryptofeargreed.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.presentation.screens.splash.components.LoadingAnimation
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants.APP_ICON
import com.iiamir.cryptofeargreed.utils.Constants.APP_NAME
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    isInDarkMode: Boolean,
    showHomeScreenAction: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(1000L)
        showHomeScreenAction()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isSystemInDarkMode = isSystemInDarkTheme()
        val darkLogo = painterResource(id = R.drawable.app_logo_dark)
        val lightLogo = painterResource(id = R.drawable.app_logo_light)

        val appIconPainter = remember(isInDarkMode) {
            if (isSystemInDarkMode || isInDarkMode)
                darkLogo
            else
                lightLogo
        }

        Image(
            modifier = Modifier
                .height(splashScreenAppIconHeightSize)
                .width(splashScreenAppIconWidthSize),
            painter = appIconPainter,
            contentDescription = APP_ICON,
        )

        Spacer(modifier = Modifier.height(FIVE))

        Text(
            text = APP_NAME,
            maxLines = 1,
            style = splashScreenAppNameTextStyle
        )

        Spacer(modifier = Modifier.height(TEN))

        LoadingAnimation()
    }
}