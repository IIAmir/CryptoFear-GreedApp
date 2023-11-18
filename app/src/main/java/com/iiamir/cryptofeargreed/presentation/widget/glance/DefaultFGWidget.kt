package com.iiamir.cryptofeargreed.presentation.widget.glance

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import com.iiamir.cryptofeargreed.data.local.FGDataDao
import com.iiamir.cryptofeargreed.presentation.widget.glance.components.ErrorContent
import com.iiamir.cryptofeargreed.presentation.widget.glance.components.ListContent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultFGWidget : GlanceAppWidget() {

    object DatabaseDao : KoinComponent {
        val dataDao: FGDataDao by inject()
    }

    @Composable
    override fun Content() {
        val dataResult = DatabaseDao
            .dataDao
            .getFGDataForWidget()
            .map { it.toFGDataModel() }

        if (dataResult.isNotEmpty()) {
            ListContent(dataResult = dataResult)
        } else {
            ErrorContent()
        }
    }

}