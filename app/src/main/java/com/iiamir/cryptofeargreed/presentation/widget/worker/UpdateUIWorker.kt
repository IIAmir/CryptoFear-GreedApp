package com.iiamir.cryptofeargreed.presentation.widget.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

fun Context.startUpdateUiWorker() {
    val networkConstraint =
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    val request = PeriodicWorkRequest
        .Builder(FGWidgetWorker::class.java, 15, TimeUnit.MINUTES)
        .setConstraints(networkConstraint)
        .build()

    val uniqueTag = "FEAR_GREED_WIDGET"

    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork(
            uniqueTag,
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
}