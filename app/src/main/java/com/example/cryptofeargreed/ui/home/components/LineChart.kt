package com.example.cryptofeargreed.ui.home.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import com.example.cryptofeargreed.data.remote.dto.FGDataModel
import com.example.cryptofeargreed.data.util.timeStampToMonthConverter

@Composable
fun LineChart(
    data: List<FGDataModel>,
    modifier: Modifier = Modifier,
) {
    val spacing = 30f

    val currentUpperValue = remember {
        (data.maxOfOrNull { it.indexValue.toDouble() }?.plus(1)) ?: 0
    }.toDouble()

    val currentLowerValue = remember {
        (data.minOfOrNull { it.indexValue.toDouble() } ?: 0)
    }.toDouble()

    val density = LocalDensity.current

    val textPaint = remember {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(
        modifier = modifier
    ) {
        val spacePerDay = (size.width - spacing) / data.size

        (data.indices step 2).forEach { i ->
            val day = timeStampToMonthConverter(data[i].timeStamp)
            drawContext.canvas.nativeCanvas.apply {
                rotate(
                    degrees = -90f,
                    pivot = Offset(spacing + i * spacePerDay, size.height)
                ) {
                    drawText(
                        day,
                        spacing + i * spacePerDay - 20,
                        size.height + 8,
                        textPaint
                    )
                }
            }
        }

        data.indices.forEach { i ->
            val info = data[i]
            val ratio =
                (info.indexValue - currentLowerValue) / (currentUpperValue - currentLowerValue)

            val x1 = spacing + i * spacePerDay
            val y1 = size.height - spacing - (ratio * size.height).toFloat()
            val y2 = size.height - spacing

            drawLine(
                color = Color.Red,
                start = Offset(x1, y2),
                end = Offset(x1, y1),
                strokeWidth = 2f
            )

            drawCircle(
                color = Color.White,
                radius = 6f,
                center = Offset(x1, y1)
            )

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    info.indexValue.toString(),
                    x1,
                    y1 - spacing / 2,
                    textPaint
                )
            }
        }
    }
}