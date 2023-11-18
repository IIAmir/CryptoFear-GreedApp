package com.iiamir.cryptofeargreed.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

val GraphIndexValueDateTextSize: TextUnit
    @Composable
    get() = TWELVE_SP * windowSizeScaleNumbers()

val GraphIndexValueTextSize: TextUnit
    @Composable
    get() = TEN_SP * windowSizeScaleNumbers()

val splashScreenAppNameTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontWeight = FontWeight.Bold,
        fontSize = SIXTEEN_SP * windowSizeScaleNumbers()
    )

val toolbarTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        textAlign = TextAlign.Start,
        fontSize = TWENTY_SP * windowSizeScaleNumbers()
    )

val menuItemTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = FOURTEEN_SP * windowSizeScaleNumbers()
    )

val nowClassificationValueTextStyle: SpanStyle
    @Composable
    get() = SpanStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = EIGHTEEN_SP * windowSizeScaleNumbers()
    )

val nowIndexValueTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = Color.White,
        fontSize = TWENTY_TWO_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center
    )

val nowDateTimeTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = EIGHTEEN_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center
    )

val timeUntilUpdateTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = EIGHTEEN_SP * windowSizeScaleNumbers(),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

val timeUntilUpdateTimeTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = SIXTEEN_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center
    )

val chartCategoryNameTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = SIXTEEN_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center
    )

val dateTimeItemsTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = SIXTEEN_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center
    )

val classificationValueItemTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = SIXTEEN_SP * windowSizeScaleNumbers(),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

val indexValueItemTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = TWENTY_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center,
        color = Color.White
    )

val allDataCategoryTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = SIXTEEN_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSecondary,
    )

val pieChartDataTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = FOURTEEN_SP * windowSizeScaleNumbers(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSecondary,
    )

// Dialog Text Styles

val AboutDialogTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = EIGHTEEN_SP,
        fontWeight = FontWeight.Bold
    )

val AboutDialogTitleTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = ExtremeFearIndexValueColor,
        fontSize = EIGHTEEN_SP,
        fontWeight = FontWeight.Bold
    )

val AboutDialogContentTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = FOURTEEN_SP,
    )

val AboutDialogForMoreInformationTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        color = ExtremeFearIndexValueColor,
        fontSize = SIXTEEN_SP,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    )