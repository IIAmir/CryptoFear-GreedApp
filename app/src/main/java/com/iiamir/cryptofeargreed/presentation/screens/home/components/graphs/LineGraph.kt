package com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.presentation.screens.home.components.animations.animatableView
import com.iiamir.cryptofeargreed.presentation.screens.home.components.animations.repeatableAnimation
import com.iiamir.cryptofeargreed.presentation.theme.GraphIndexValueDateTextSize
import com.iiamir.cryptofeargreed.presentation.theme.TWO
import com.iiamir.cryptofeargreed.presentation.theme.currentIndexValueCircleRadius
import com.iiamir.cryptofeargreed.presentation.theme.graphsHorizontalSpace

@Composable
fun LineGraph(
    data: List<FGDataModel>,
    modifier: Modifier = Modifier,
    isInDarkMode: Boolean
) {
    val spacing = graphsHorizontalSpace
    val graphColor = Color.Red
    val transparentGraphColor = remember { graphColor.copy(alpha = 0.5f) }

    val canvasColors =
        if (isSystemInDarkTheme() || isInDarkMode) android.graphics.Color.WHITE else android.graphics.Color.BLACK

    val currentUpperValue = remember(data) {
        (data.maxOfOrNull { it.indexValue }) ?: 0
    }.toFloat()

    val currentLowerValue = remember(data) {
        (data.minOfOrNull { it.indexValue } ?: 0)
    }.toFloat()

    val density = LocalDensity.current
    val indexValueDateTextSize = GraphIndexValueDateTextSize

    val indexValueDatePaint = remember {
        Paint().apply {
            color = canvasColors
            textAlign = Paint.Align.CENTER
            textSize = density.run { indexValueDateTextSize.toPx() }
        }
    }

    val animateCurrentIndexValue = repeatableAnimation(
        initialValue = currentIndexValueCircleRadius.first,
        targetValue = currentIndexValueCircleRadius.second
    )
    val pathAnimation = animatableView()

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

        val strokePath = Path().apply {
            val height = size.height
            data.indices.forEach { i ->
                val info = data[i]
                val ratio =
                    (info.indexValue - currentLowerValue) / (currentUpperValue - currentLowerValue)

                val x1 = spacing + i * spacePerDay
                val y1 = height - spacing - (ratio * height)

                if (i == 0)
                    moveTo(x1, y1)

                lineTo(x1, y1)


                if (i == data.size - 1) {

                    drawCircle(
                        color = Color.Red,
                        radius = animateCurrentIndexValue,
                        center = Offset(x1, y1)
                    )
                }
            }
        }

        val outPath = Path()
        PathMeasure().apply {
            setPath(strokePath, false)
            getSegment(0f, pathAnimation * length, outPath)
        }

        drawPath(
            path = outPath,
            color = graphColor,
            style = Stroke(
                width = TWO.toPx(),
                cap = StrokeCap.Round
            )
        )

        val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
            lineTo(size.width - spacePerDay, size.height - spacing)
            lineTo(spacing, size.height - spacing)
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )
    }
}