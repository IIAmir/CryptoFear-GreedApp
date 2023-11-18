package com.iiamir.cryptofeargreed.presentation.widget.glance.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.Text
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.presentation.widget.worker.WidgetRefreshAction
import com.iiamir.cryptofeargreed.utils.Constants

@Composable
fun ErrorContent() {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .background(ImageProvider(R.drawable.widget_shape))
            .cornerRadius(FIFTEEN),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = Constants.ERROR_OCCURRED_WIDGET_TEXT,
            style = ErrorContentTextStyle
        )
        Spacer(modifier = GlanceModifier.height(FIVE))
        Image(
            provider = ImageProvider(R.drawable.baseline_refresh_24),
            contentDescription = Constants.REFRESH_WIDGET,
            modifier = GlanceModifier
                .size(TWENTY_FIVE)
                .clickable(onClick = actionRunCallback<WidgetRefreshAction>())
        )
    }
}