package com.iiamir.cryptofeargreed.presentation.widget.glance.components

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*
import androidx.glance.text.Text
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.presentation.theme.TWENTY_FIVE
import com.iiamir.cryptofeargreed.presentation.theme.TWO
import com.iiamir.cryptofeargreed.presentation.theme.ToolbarAppNameTextStyle
import com.iiamir.cryptofeargreed.presentation.widget.worker.WidgetRefreshAction
import com.iiamir.cryptofeargreed.utils.Constants.REFRESH_WIDGET
import com.iiamir.cryptofeargreed.utils.Constants.TOP_BAR_WIDGET_TEXT

@Composable
fun ToolbarWidget() {
    Row(
        modifier = GlanceModifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = TOP_BAR_WIDGET_TEXT,
            style = ToolbarAppNameTextStyle,
            modifier = GlanceModifier
                .defaultWeight()
        )
        Spacer(modifier = GlanceModifier.width(TWO))
        Image(
            provider = ImageProvider(R.drawable.baseline_refresh_24),
            contentDescription = REFRESH_WIDGET,
            modifier = GlanceModifier
                .size(TWENTY_FIVE)
                .clickable(onClick = actionRunCallback<WidgetRefreshAction>())
        )
    }
}