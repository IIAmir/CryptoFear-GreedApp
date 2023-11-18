package com.iiamir.cryptofeargreed.presentation.widget.glance.components

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
import com.iiamir.cryptofeargreed.utils.Constants.NOW_INDEX_VALUE_TXT
import com.iiamir.cryptofeargreed.utils.indexColorForWidget

@Composable
fun NowFGWidgetItem(data: FGDataModel) {
    Column(
        modifier = GlanceModifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                style = NowFearNGreedIndexTextStyle
            )
        }
        Spacer(modifier = GlanceModifier.height(TWO))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = NOW_INDEX_VALUE_TXT,
                style = FearNGreedIndexDateTextStyle
            )

            Spacer(modifier = GlanceModifier.width(TWO))

            Text(
                text = data.valueClassification,
                style = FearNGreedIndexClassificationTextStyle.copy(
                    color = ColorProvider(Color(data.valueClassificationColor))
                )
            )
        }

        Text(
            text = data.timeStamp,
            style = NowFearNGreedIndexDateTextStyle
        )
    }
}