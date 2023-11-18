package com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.iiamir.cryptofeargreed.presentation.theme.*

@Composable
fun PieGraph(
    data: List<Pair<String, Int>>,
    radiusOuter: Dp = pieGraphRadiusOuterSize,
    chartBaeWidth: Dp = pieGraphChartBaeWidth,
    animationDuration: Int = 1000,
    modifier: Modifier
) {
    key(data) {
        val animationPlayed = remember {
            mutableStateOf(false)
        }

        val dataList by remember(data) {
            mutableStateOf(data)
        }

        val floatValue = mutableListOf<Float>()

        dataList.forEachIndexed { index, pair ->
            val totalSum = data.sumOf { it.second }
            floatValue.add(
                index,
                360 * pair.second.toFloat() / totalSum.toFloat()
            )
        }

        val colors = listOf(
            ExtremeFearIndexValueColor,
            FearIndexValueColor,
            NeutralIndexValueColor,
            GreedIndexValueColor,
            ExtremeGreedIndexValueColor
        )

        var lastValue = 0f

        val animateSize by animateFloatAsState(
            targetValue = if (animationPlayed.value) radiusOuter.value * 2f else 0f,
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = 0,
                easing = LinearOutSlowInEasing
            )
        )

        val animateRotation by animateFloatAsState(
            targetValue = if (animationPlayed.value) 90 * 11f else 0f,
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = 0,
                easing = LinearOutSlowInEasing
            )
        )

        LaunchedEffect(key1 = true) {
            animationPlayed.value = true
        }

        Row(
            modifier = modifier.padding(start = FIFTEEN, end = TEN),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier.size(animateSize.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier
                        .size(radiusOuter * 2f)
                        .rotate(animateRotation)
                ) {
                    floatValue.forEachIndexed { index, value ->
                        drawArc(
                            color = colors[index],
                            lastValue,
                            value,
                            useCenter = false,
                            style = Stroke(width = chartBaeWidth.toPx(), cap = StrokeCap.Round)
                        )
                        lastValue += value
                    }
                }
            }

            Column(modifier = Modifier.padding(start = TWENTY)) {
                dataList.forEachIndexed { index, pair ->
                    PieGraphItem(
                        classificationName = pair.first,
                        classificationSize = pair.second,
                        classificationColor = colors[index]
                    )
                }
            }
        }
    }
}