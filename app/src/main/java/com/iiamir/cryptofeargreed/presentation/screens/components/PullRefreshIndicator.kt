package com.iiamir.cryptofeargreed.presentation.screens.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshIndicator(
    modifier: Modifier,
    isRefreshing: Boolean,
    pullRefreshState: PullRefreshState,
    contentColor: Color = MaterialTheme.colors.onSecondary,
    backgroundColor: Color = MaterialTheme.colors.primary,
    scale: Boolean = true
) {
    androidx.compose.material.pullrefresh.PullRefreshIndicator(
        modifier = modifier,
        refreshing = isRefreshing,
        state = pullRefreshState,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        scale = scale
    )
}