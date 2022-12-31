package com.example.cryptofeargreed.utils

import androidx.compose.ui.graphics.Color
import com.example.cryptofeargreed.ui.theme.*

fun indexColor(valueClassification: String): Color {
    return when (valueClassification) {
        "Extreme Fear" -> {
            ExtremeFearIndexValueColor
        }
        "Fear" -> {
            FearIndexValueColor
        }
        "Neutral" -> {
            NeutralIndexValueColor
        }
        "Greed" -> {
            GreedIndexValueColor
        }
        "Extreme Greed" -> {
            ExtremeGreedIndexValueColor
        }
        else -> { NeutralIndexValueColor }
    }
}