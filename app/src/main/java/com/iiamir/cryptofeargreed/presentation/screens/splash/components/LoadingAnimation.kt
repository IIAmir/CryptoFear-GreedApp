package com.iiamir.cryptofeargreed.presentation.screens.splash.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iiamir.cryptofeargreed.presentation.theme.TWO

@Composable
fun LoadingAnimation() {
    val minAlpha = 0.1f
    val delayUnit = 200
    val spaceSize = TWO

    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun animateAlphaWithDelay(delay: Int) = infiniteTransition.animateFloat(
        initialValue = minAlpha,
        targetValue = minAlpha,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = delayUnit * 6
                minAlpha at delay with LinearEasing
                1f at delay + delayUnit with LinearEasing
                minAlpha at delay + delayUnit * 2
            }
        )
    )

    val alpha1 by animateAlphaWithDelay(0)
    val alpha2 by animateAlphaWithDelay(delayUnit)
    val alpha3 by animateAlphaWithDelay(delayUnit * 2)
    val alpha4 by animateAlphaWithDelay(delayUnit * 3)
    val alpha5 by animateAlphaWithDelay(delayUnit * 4)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Dot(alpha1)
        Spacer(Modifier.width(spaceSize))
        Dot(alpha2)
        Spacer(Modifier.width(spaceSize))
        Dot(alpha3)
        Spacer(Modifier.width(spaceSize))
        Dot(alpha4)
        Spacer(Modifier.width(spaceSize))
        Dot(alpha5)
    }
}
