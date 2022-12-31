package com.example.cryptofeargreed.ui.home.components

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptofeargreed.data.remote.dto.FGDataModel
import com.example.cryptofeargreed.data.util.timeStampToYearConverter
import com.example.cryptofeargreed.ui.theme.ExtremeFearIndexValueColor
import com.example.cryptofeargreed.ui.theme.ExtremeGreedIndexValueColor
import com.example.cryptofeargreed.ui.theme.NeutralIndexValueColor
import com.example.cryptofeargreed.utils.indexColor

@Composable
fun CustomComponent(
    fgDataModel: FGDataModel,
    canvasSize: Dp = 230.dp,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: List<Pair<Float, Color>> = listOf(
        0.6f to ExtremeFearIndexValueColor.copy(alpha = 0.3f),
        0.75f to NeutralIndexValueColor.copy(alpha = 0.3f),
        1f to ExtremeGreedIndexValueColor.copy(alpha = 0.3f)
    ),
    backgroundIndicatorStrokeWidth: Float = 50f,
    foregroundIndicatorColor: List<Pair<Float, Color>> = listOf(
        0.6f to ExtremeFearIndexValueColor,
        0.75f to NeutralIndexValueColor,
        1f to ExtremeGreedIndexValueColor
    ),
    foregroundIndicatorStrokeWidth: Float = 50f,
) {
    val color = indexColor(fgDataModel.valueClassification)

    val allowedIndicatorValue by remember {
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
                val componentSize = size / 0.99f
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
            withStyle(style = SpanStyle(
                color = Color.White,
            )) {
                append("Now: ")
            }
            withStyle(style = SpanStyle(
                color = color
            )) {
                append(fgDataModel.valueClassification)
            }
        }, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = fgDataModel.indexValue.toString(),
                fontSize = 22.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = timeStampToYearConverter(fgDataModel.timeStamp),
            color = Color.White
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: List<Pair<Float, Color>> = listOf(
        0.6f to ExtremeFearIndexValueColor,
        0.75f to NeutralIndexValueColor,
        1f to ExtremeGreedIndexValueColor
    ),
    indicatorStrokeWidth: Float,
) {
    drawArc(
        size = componentSize,
        brush = Brush.sweepGradient(
            *indicatorColor.toTypedArray(),
            center = Offset(center.x, size.height)
        ),
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: List<Pair<Float, Color>> = listOf(
        0.6f to ExtremeFearIndexValueColor,
        0.75f to NeutralIndexValueColor,
        1f to ExtremeGreedIndexValueColor
    ),
    indicatorStrokeWidth: Float,
) {
    drawArc(
        size = componentSize,
        brush = Brush.sweepGradient(
            *indicatorColor.toTypedArray(),
            center = Offset(center.x, size.height)
        ),
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}