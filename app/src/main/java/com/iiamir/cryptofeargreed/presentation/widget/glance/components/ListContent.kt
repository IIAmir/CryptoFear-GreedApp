package com.iiamir.cryptofeargreed.presentation.widget.glance.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.*
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants

@Composable
fun ListContent(
    dataResult: List<FGDataModel>
) {
    Column(
        modifier = GlanceModifier.fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .background(ImageProvider(R.drawable.widget_shape))
            .cornerRadius(FIFTEEN)
            .padding(TEN)
    ) {
        ToolbarWidget()
        Spacer(modifier = GlanceModifier.height(FIVE))
        NowFGWidgetItem(data = dataResult[Constants.GET_TODAY_FG_BY_INDEX])
        Spacer(modifier = GlanceModifier.height(FIVE))
        FGWidgetItem(
            data = dataResult[Constants.GET_YESTERDAY_FG_BY_INDEX],
            dataDay = Constants.YESTERDAY_TXT
        )
        Spacer(modifier = GlanceModifier.height(TWO))
        FGWidgetItem(
            data = dataResult[Constants.GET_LAST_WEEK_FG_BY_INDEX],
            dataDay = Constants.LAST_WEEK_TXT
        )
        Spacer(modifier = GlanceModifier.height(TWO))
        FGWidgetItem(
            data = dataResult[Constants.GET_LAST_MONTH_FG_BY_INDEX],
            dataDay = Constants.LAST_MONTH_TXT
        )
    }
}