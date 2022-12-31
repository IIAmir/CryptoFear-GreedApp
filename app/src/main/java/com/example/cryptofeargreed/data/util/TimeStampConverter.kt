package com.example.cryptofeargreed.data.util

import java.text.SimpleDateFormat
import java.util.*

fun timeStampToYearConverter(ts: Int): String =
    SimpleDateFormat("dd MMMM, yyyy", Locale.US).format(ts.toLong() * 1000L)

fun timeStampToMonthConverter(ts: Int): String =
    SimpleDateFormat("dd MMM", Locale.US).format(ts.toLong() * 1000L)

fun dateStampConverter(ts: Int): String {
    val hours = ts / 3600
    val minutes = (ts % 3600) / 60
    val seconds = ts % 60

    return String.format("%02d Hours %02d Minutes %02d Seconds ", hours, minutes, seconds)
}