package com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.iiamir.cryptofeargreed.presentation.theme.*

@Composable
fun PieGraphItem(
    classificationName: String,
    classificationSize: Int,
    classificationColor: Color
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(THIRTY * windowSizeScaleNumbers()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(TWENTY * windowSizeScaleNumbers())
                .clip(RoundedCornerShape(FIVE))
                .background(classificationColor)
        )

        Text(
            modifier = Modifier.padding(start = TWO),
            text = " $classificationSize days $classificationName",
            style = pieChartDataTextStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}