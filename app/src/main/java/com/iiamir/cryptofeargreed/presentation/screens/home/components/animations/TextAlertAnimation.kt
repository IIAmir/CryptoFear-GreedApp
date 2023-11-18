package com.iiamir.cryptofeargreed.presentation.screens.home.components.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.iiamir.cryptofeargreed.presentation.theme.timeUntilUpdateTimeTextStyle

@Composable
fun TextAlertAnimation(
    textAlert: String,
    initialValueColor: Color = MaterialTheme.colors.secondary,
    targetValueColor: Color = MaterialTheme.colors.onSecondary,
    style: TextStyle = timeUntilUpdateTimeTextStyle,
    durationMills: Int = 500
) {

    val transition = rememberInfiniteTransition()
    val color by transition.animateColor(
        initialValue = initialValueColor,
        targetValue = targetValueColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMills,
            ),
            repeatMode = RepeatMode.Reverse,
        )
    )
    Text(
        text = textAlert,
        color = color,
        maxLines = 1,
        style = style
    )

}