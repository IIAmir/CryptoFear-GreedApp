package com.iiamir.cryptofeargreed.presentation.screens.home.components.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenDataState
import com.iiamir.cryptofeargreed.presentation.screens.home.components.shimmer.ShimmerContent
import com.iiamir.cryptofeargreed.presentation.theme.TWENTY
import com.iiamir.cryptofeargreed.utils.Constants

@Composable
fun LoadingAndErrorContent(
    homeScreenDataState: HomeScreenDataState,
    onRetryActionClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (homeScreenDataState.isLoading) {
            ShimmerContent()
        } else if (homeScreenDataState.error.isNullOrBlank()) {
            Button(
                onClick = onRetryActionClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(TWENTY)
            ) {
                Text(text = Constants.RETRY_TXT, color = MaterialTheme.colors.onSecondary)
            }
        }
    }
}