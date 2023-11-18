package com.iiamir.cryptofeargreed.presentation.widget.worker

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.iiamir.cryptofeargreed.domain.repository.FGDataRepository
import com.iiamir.cryptofeargreed.presentation.widget.glance.DefaultFGWidget
import com.iiamir.cryptofeargreed.presentation.widget.glance.TodayFGWidget
import com.iiamir.cryptofeargreed.utils.Constants.FAILED_REQUEST
import com.iiamir.cryptofeargreed.utils.Constants.FAILURE_REASON
import com.iiamir.cryptofeargreed.utils.Constants.NONE
import com.iiamir.cryptofeargreed.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class FGWidgetWorker(
    private val appContext: Context,
    private val repository: FGDataRepository,
    workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        var getResponseIsSuccessful = false

        repository.getAllFGDataRepo().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    DefaultFGWidget().apply {
                        update(appContext, glanceID(DefaultFGWidget()))
                    }
                    TodayFGWidget().apply {
                        update(appContext, glanceID(DefaultFGWidget()))
                    }
                    getResponseIsSuccessful = true
                }
                else -> {}
            }
        }.collect()

        return if (getResponseIsSuccessful) {
            Result.success(
                Data
                    .Builder()
                    .putString(FAILURE_REASON, NONE)
                    .build()
            )
        } else {
            Result.failure(
                Data
                    .Builder()
                    .putString(FAILURE_REASON, FAILED_REQUEST)
                    .build(),
            )
        }
    }

    private suspend fun glanceID(widget: GlanceAppWidget): GlanceId {
        return GlanceAppWidgetManager(appContext)
            .getGlanceIds(widget::class.java)
            .firstOrNull()!!
    }

}