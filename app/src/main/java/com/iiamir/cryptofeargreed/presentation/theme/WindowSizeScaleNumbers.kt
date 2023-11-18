package com.iiamir.cryptofeargreed.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.iiamir.cryptofeargreed.utils.WindowInfo

@Composable
fun windowSizeScaleNumbers(): Float {
    val configuration = LocalConfiguration.current

    val windowInfo = WindowInfo(
        screenWidthInfo = when {
            configuration.screenWidthDp < 380 -> WindowInfo.WindowType.Compact
            configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo = when {
            configuration.screenHeightDp < 480 -> WindowInfo.WindowType.Compact
            configuration.screenHeightDp < 900 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )

    return when (windowInfo.screenWidthInfo) {
        is WindowInfo.WindowType.Compact -> {
            1f
        }
        is WindowInfo.WindowType.Medium -> {
            1.11f
        }
        is WindowInfo.WindowType.Expanded -> {
            1.33f
        }
    }
}