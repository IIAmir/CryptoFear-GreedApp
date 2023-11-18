package com.iiamir.cryptofeargreed.presentation.screens.home.components.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.iiamir.cryptofeargreed.presentation.theme.*

@Composable
fun FGItemShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(FIFTY)
            .padding(start = TEN, end = TEN),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(0.8f),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(TEN))

            Box(
                modifier = Modifier
                    .width(NINETY)
                    .height(TWENTY)
                    .clip(RoundedCornerShape(FIVE))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(FIVE))

            Box(
                modifier = Modifier
                    .width(SIXTY)
                    .height(TWENTY)
                    .clip(RoundedCornerShape(FIVE))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(FIVE))

            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.surface,
            )
        }

        Box(
            modifier = Modifier
                .size(FOURTY * windowSizeScaleNumbers())
                .clip(CircleShape)
                .shimmerEffect()
        )
    }
    Spacer(modifier = Modifier.height(FIVE))
}