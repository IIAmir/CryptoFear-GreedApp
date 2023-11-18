package com.iiamir.cryptofeargreed.presentation.screens.home.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.iiamir.cryptofeargreed.presentation.theme.*

@Composable
fun ShimmerContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentColor = MaterialTheme.colors.secondary,
            backgroundColor = MaterialTheme.colors.secondary,
            shape = RoundedCornerShape(bottomStart = TWENTY, bottomEnd = TWENTY),
            elevation = EIGHT
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(NINETY * windowSizeScaleNumbers()))

                Box(
                    modifier = Modifier
                        .width(SEVENTY)
                        .height(TWENTY_FIVE)
                        .clip(RoundedCornerShape(FIVE))
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(TWENTY))

                Box(
                    modifier = Modifier
                        .size(FOURTY * windowSizeScaleNumbers())
                        .clip(CircleShape)
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(TEN))

                Box(
                    modifier = Modifier
                        .width(NINETY)
                        .height(TWENTY)
                        .clip(RoundedCornerShape(FIVE))
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(SEVENTY * windowSizeScaleNumbers()))

                Box(
                    modifier = Modifier
                        .width(TWO_HUNDRED)
                        .height(TWENTY)
                        .clip(RoundedCornerShape(FIVE))
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(TEN))

                Box(
                    modifier = Modifier
                        .width(TWO_HUNDRED)
                        .height(TWENTY)
                        .clip(RoundedCornerShape(FIVE))
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(TEN))
            }
        }

        Spacer(modifier = Modifier.height(FIFTEEN))

        FGItemShimmer()
        FGItemShimmer()
        FGItemShimmer()

        Spacer(modifier = Modifier.height(TWENTY))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(TWO_HUNDRED)
                    .height(TWENTY)
                    .clip(RoundedCornerShape(FIVE))
                    .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(TEN))
    }

}