package com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.presentation.screens.home.components.animations.animatableView
import com.iiamir.cryptofeargreed.presentation.theme.GraphIndexValueDateTextSize
import com.iiamir.cryptofeargreed.presentation.theme.GraphIndexValueTextSize
import com.iiamir.cryptofeargreed.presentation.theme.barGraphStrokeWidth
import com.iiamir.cryptofeargreed.presentation.theme.graphsHorizontalSpace

@Composable
fun BarGraph(
    data: List<FGDataModel>,
    modifier: Modifier = Modifier,
    isInDarkMode: Boolean
) {
    val spacing = graphsHorizontalSpace

    val canvasColors =
        if (isSystemInDarkTheme() || isInDarkMode) android.graphics.Color.WHITE else android.graphics.Color.BLACK

    val currentUpperValue = remember(data) {
        (data.maxOfOrNull { it.indexValue }) ?: 0
    }.toFloat()

    val currentLowerValue = remember(data) {
        (data.minOfOrNull { it.indexValue } ?: 0)
    }.toFloat()

    val density = LocalDensity.current
    val strokeWidth = barGraphStrokeWidth
    val indexValueDateTextSize = GraphIndexValueDateTextSize
    val indexValueTextSize = GraphIndexValueTextSize

    val indexValueDatePaint = remember {
        Paint().apply {
            color = canvasColors
            textAlign = Paint.Align.CENTER
            textSize = density.run { indexValueDateTextSize.toPx() }
        }
    }

    val indexValuePaint = remember {
        Paint().apply {
            color = canvasColors
            textAlign = Paint.Align.CENTER
            textSize = density.run { indexValueTextSize.toPx() }
        }
    }

    val barAnimation = animatableView()

    Canvas(
        modifier = modifier
    ) {
        val spacePerDay = (size.width - spacing) / data.size
        (data.indices step 2).forEach { i ->
            val day = data[i].timeStamp.subSequence(0, 6)
            drawContext.canvas.nativeCanvas.apply {
                rotate(
                    degrees = -90f,
                    pivot = Offset(spacing + i * spacePerDay, size.height)
                ) {
                    drawText(
                        day.toString(),
                        spacing + i * spacePerDay - spacing,
                        size.height + 8,
                        indexValueDatePaint
                    )
                }
            }
        }

        data.indices.forEach { i ->
            val info = data[i]
            val ratio =
                (info.indexValue - currentLowerValue) / (currentUpperValue - currentLowerValue)

            val x1 = spacing + i * spacePerDay * barAnimation
            val y1 = size.height - spacing - (ratio * size.height) * barAnimation
            val y2 = size.height - spacing

            drawLine(
                color = Color.Red,
                start = Offset(x1, y2),
                end = Offset(x1, y1 - 5),
                cap = StrokeCap.Round,
                strokeWidth = strokeWidth
            )

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    info.indexValue.toString(),
                    x1,
                    y1 - 15,
                    indexValuePaint
                )
            }
        }
    }
}