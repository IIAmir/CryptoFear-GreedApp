package com.iiamir.cryptofeargreed.presentation.widget.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.*
import java.time.Duration
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
fun Context.startUpdateUiWorker() {
    val networkConstraint =
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    val timeConstraint =
        Constraints.Builder().setTriggerContentUpdateDelay(Duration.ofSeconds(5L)).build()

    val request = PeriodicWorkRequest
        .Builder(FGWidgetWorker::class.java, 15, TimeUnit.MINUTES)
        .setConstraints(networkConstraint)
        .setConstraints(timeConstraint)
        .build()

    val uniqueTag = "FEAR_GREED_WIDGET"

    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork(
            uniqueTag,
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
}