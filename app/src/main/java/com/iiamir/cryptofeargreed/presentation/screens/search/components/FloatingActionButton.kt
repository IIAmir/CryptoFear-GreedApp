package com.iiamir.cryptofeargreed.presentation.screens.search.components

import androidx.compose.animation.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.iiamir.cryptofeargreed.utils.Constants.SCROLL_ACTION_BUTTON

@Composable
fun FloatingActionButton(
    floatingActionButtonVisibility: Boolean,
    floatingActionButtonClicked: () -> Unit
) {
    AnimatedVisibility(
        visible = floatingActionButtonVisibility,
        enter = fadeIn() + slideIn(initialOffset = { IntOffset(0, 100) }),
        exit = fadeOut() + slideOut(targetOffset = { IntOffset(0, 100) })
    ) {
        FloatingActionButton(
            onClick = floatingActionButtonClicked,
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = SCROLL_ACTION_BUTTON,
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }
}