package com.iiamir.cryptofeargreed.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorPalette = darkColors(
    primary = DarkContentColor,
    secondary = DarkTopBarColor,
    onSecondary = DarkTextColor,
    surface = DarkDividerColor,
)

private val LightColorPalette = lightColors(
    primary = LightContentColor,
    secondary = LightTopBarColor,
    onSecondary = LightTextColor,
    surface = LightDividerColor,
)

@Composable
fun CryptoFearGreedTheme(
    darkThemeBySystem: Boolean = isSystemInDarkTheme(),
    darkThemeByUser: Boolean,
    content: @Composable () -> Unit
) {
    val colors = when {
        darkThemeByUser -> DarkColorPalette
        !darkThemeByUser -> LightColorPalette
        darkThemeBySystem -> DarkColorPalette
        else -> LightColorPalette
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colors.secondary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars =
                !darkThemeBySystem || !darkThemeByUser
        }
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}