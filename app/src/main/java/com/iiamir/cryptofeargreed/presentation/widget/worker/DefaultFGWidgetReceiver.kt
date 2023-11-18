package com.iiamir.cryptofeargreed.presentation.widget.worker

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.iiamir.cryptofeargreed.presentation.widget.glance.DefaultFGWidget
import com.iiamir.cryptofeargreed.presentation.widget.glance.TodayFGWidget


class DefaultFGWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: DefaultFGWidget = DefaultFGWidget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        context?.startUpdateUiWorker()
    }
}

class TodayFGWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TodayFGWidget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        context?.startUpdateUiWorker()
    }
}