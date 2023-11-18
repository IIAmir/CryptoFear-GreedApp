package com.iiamir.cryptofeargreed.presentation.screens.home.components.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun animatableView(
    key: Boolean = true,
    initialValue: Float = 0f,
    targetValue: Float = 1f,
    durationMills: Int = 2000
): Float {
    val animatableView = remember {
        Animatable(
            initialValue = initialValue
        )
    }
    LaunchedEffect(key1 = key) {
        animatableView.animateTo(
            targetValue = targetValue,
            animationSpec = tween(
                durationMills
            )
        )
    }
    return animatableView.value
}