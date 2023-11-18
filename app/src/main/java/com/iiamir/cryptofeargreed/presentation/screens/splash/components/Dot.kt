package com.iiamir.cryptofeargreed.presentation.screens.splash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.iiamir.cryptofeargreed.presentation.theme.EIGHT

@Composable
fun Dot(alpha: Float) = Box(
    Modifier
        .size(EIGHT)
        .alpha(alpha = alpha)
        .background(
            color = MaterialTheme.colors.onSecondary,
            shape = CircleShape
        )
)