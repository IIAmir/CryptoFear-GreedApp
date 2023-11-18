package com.iiamir.cryptofeargreed.presentation.widget.glance.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.unit.ColorProvider
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.indexColorForWidget

@Composable
fun FGWidgetItem(data: FGDataModel, dataDay: String) {
    Row(
        modifier = GlanceModifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = GlanceModifier
                .width(ONE_HUNDRED)
                .defaultWeight()
        ) {
            Text(
                text = dataDay,
                style = FearNGreedIndexDateTextStyle
            )

            Text(
                text = data.valueClassification,
                style = FearNGreedIndexClassificationTextStyle.copy(
                    color = ColorProvider(Color(data.valueClassificationColor))
                )
            )
        }

        Spacer(modifier = GlanceModifier.width(FIFTEEN))

        Box(
            modifier = GlanceModifier
                .size(THIRTY_FIVE)
                .background(Color(data.valueClassificationColor))
                .background(ImageProvider(indexColorForWidget(data.valueClassification)))
                .cornerRadius(TWENTY_FIVE),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = data.indexValue.toString(),
                style = FearNGreedIndexTextStyle
            )
        }
    }
}