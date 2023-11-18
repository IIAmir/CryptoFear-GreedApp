package com.iiamir.cryptofeargreed.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

val splashScreenAppIconHeightSize: Dp
    @Composable
    get() = DEFAULT_APP_ICON_HEIGHT * windowSizeScaleNumbers()

val splashScreenAppIconWidthSize: Dp
    @Composable
    get() = DEFAULT_APP_ICON_WIDTH * windowSizeScaleNumbers()

val aboutDialogWidthSize: Dp
    @Composable
    get() = DEFAULT_DIALOG_WIDTH_SIZE

val aboutDialogHeightSize: Dp
    @Composable
    get() = DEFAULT_DIALOG_HEIGHT_SIZE

val indexChartCanvasSize: Dp
    @Composable
    get() = DEFAULT_CANVAS_SIZE * windowSizeScaleNumbers()

val indexesChartHeightSize: Dp
    @Composable
    get() = DEFAULT_GRAPH_HEIGHT_SIZE * windowSizeScaleNumbers()

val fearAndGreedItemHeightSize: Dp
    @Composable
    get() = FIFTY * windowSizeScaleNumbers()

val circleShapeIndexValueSize: Dp
    @Composable
    get() = FOURTY * windowSizeScaleNumbers()

val indexGraphStrokeWidth: Float
    @Composable
    get() = 65f * windowSizeScaleNumbers()

val barGraphStrokeWidth: Float
    @Composable
    get() = 6.5f * windowSizeScaleNumbers()

val graphsHorizontalSpace: Float
    @Composable
    get() = 30f * windowSizeScaleNumbers() * windowSizeScaleNumbers()

val pieGraphRadiusOuterSize: Dp
    @Composable
    get() = SEVENTY * windowSizeScaleNumbers()

val pieGraphChartBaeWidth: Dp
    @Composable
    get() = TWENTY_FIVE * windowSizeScaleNumbers()

val currentIndexValueCircleRadius: Pair<Float, Float>
    @Composable
    get() = Pair(3f * windowSizeScaleNumbers(), 9f * windowSizeScaleNumbers())