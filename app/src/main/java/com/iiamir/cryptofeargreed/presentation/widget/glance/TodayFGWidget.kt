package com.iiamir.cryptofeargreed.presentation.widget.glance

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.data.local.FGDataDao
import com.iiamir.cryptofeargreed.presentation.theme.FIFTEEN
import com.iiamir.cryptofeargreed.presentation.theme.TEN
import com.iiamir.cryptofeargreed.presentation.widget.glance.components.NowFGWidgetItem
import com.iiamir.cryptofeargreed.presentation.widget.worker.WidgetRefreshAction
import com.iiamir.cryptofeargreed.utils.Constants
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodayFGWidget : GlanceAppWidget() {

    object DatabaseDao : KoinComponent {
        val dataDao: FGDataDao by inject()
    }

    @Composable
    override fun Content() {
        val dataResult = DatabaseDao
            .dataDao
            .getFGDataForWidget()
            .map { it.toFGDataModel() }

        Column(
            modifier = GlanceModifier.fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .background(ImageProvider(R.drawable.widget_shape))
                .cornerRadius(FIFTEEN)
                .padding(TEN)
                .clickable(onClick = actionRunCallback<WidgetRefreshAction>())
        ) {
            NowFGWidgetItem(data = dataResult[Constants.GET_TODAY_FG_BY_INDEX])
        }
    }

}