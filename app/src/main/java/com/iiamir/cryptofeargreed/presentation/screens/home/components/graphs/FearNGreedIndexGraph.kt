package com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants.NOW_INDEX_VALUE_TXT

@Composable
fun FearNGreedIndexGraph(
    fgDataModel: FGDataModel,
    canvasSize: Dp = indexChartCanvasSize,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: List<Pair<Float, Color>> = listOf(
        0.55f to ExtremeFearIndexValueColor.copy(alpha = 0.3f),
        0.65f to FearIndexValueColor.copy(alpha = 0.3f),
        0.75f to NeutralIndexValueColor.copy(alpha = 0.3f),
        0.85f to GreedIndexValueColor.copy(alpha = 0.3f),
        1f to ExtremeGreedIndexValueColor.copy(alpha = 0.3f)
    ),
    backgroundIndicatorStrokeWidth: Float = indexGraphStrokeWidth,
    foregroundIndicatorColor: List<Pair<Float, Color>> = listOf(
        0.55f to ExtremeFearIndexValueColor,
        0.65f to FearIndexValueColor,
        0.75f to NeutralIndexValueColor,
        0.85f to GreedIndexValueColor,
        1f to ExtremeGreedIndexValueColor
    ),
    foregroundIndicatorStrokeWidth: Float = indexGraphStrokeWidth,
) {
    val allowedIndicatorValue by remember(fgDataModel.indexValue) {
        mutableStateOf(fgDataModel.indexValue)
    }

    var animatedIndicatorValue by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage =
        (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = buildAnnotatedString {
            withStyle(
                style = nowClassificationValueTextStyle
            ) {
                append(NOW_INDEX_VALUE_TXT)
            }
            withStyle(
                style = nowClassificationValueTextStyle.copy(
                    color = Color(fgDataModel.valueClassificationColor),
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(fgDataModel.valueClassification)
            }
        })

        Spacer(modifier = Modifier.height(TEN))

        Box(
            modifier = Modifier
                .size(circleShapeIndexValueSize)
                .clip(CircleShape)
                .background(Color(fgDataModel.valueClassificationColor)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = allowedIndicatorValue.toString(),
                style = nowIndexValueTextStyle
            )
        }

        Spacer(modifier = Modifier.height(TEN))

        Text(
            text = fgDataModel.timeStamp,
            style = nowDateTimeTextStyle
        )
    }
}