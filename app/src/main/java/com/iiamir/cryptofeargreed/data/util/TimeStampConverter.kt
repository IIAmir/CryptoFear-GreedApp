package com.iiamir.cryptofeargreed.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.iiamir.cryptofeargreed.utils.Constants.ALL
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

// ts = TimeStamp

fun timeStampToYearConverter(ts: Int): String =
    SimpleDateFormat("dd MMMM, yyyy", Locale.US).format(ts.toLong() * 1000L)

fun dateStampConverter(ts: Int): String {
    return String.format(
        "%02d Hours %02d Minutes %02d Seconds ",
        ts / 3600,
        (ts % 3600) / 60,
        ts % 60
    )
}

fun calculatingPieGraphCategory(): List<String> {
    val currentYear = LocalDate.now().year
    return listOf(ALL) + (2018..currentYear).map { it.toString() }
}