package com.iiamir.cryptofeargreed.presentation.screens.home.components.animations

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

@Composable
fun repeatableAnimation(
    initialValue: Float,
    targetValue: Float,
    duration: Int = 500,
    repeatMode: RepeatMode = RepeatMode.Reverse
): Float {
    return rememberInfiniteTransition().animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(duration),
            repeatMode = repeatMode
        )
    ).value
}