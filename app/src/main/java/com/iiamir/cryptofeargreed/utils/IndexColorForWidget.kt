package com.iiamir.cryptofeargreed.utils

import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.utils.Constants.EXTREME_FEAR
import com.iiamir.cryptofeargreed.utils.Constants.EXTREME_GREED
import com.iiamir.cryptofeargreed.utils.Constants.FEAR
import com.iiamir.cryptofeargreed.utils.Constants.GREED
import com.iiamir.cryptofeargreed.utils.Constants.NEUTRAL

// Glance Widget Not Supported CornerRadius (ApiLevel < 31)
fun indexColorForWidget(valueClassification: String): Int {
    return when (valueClassification) {
        EXTREME_FEAR -> R.drawable.extreme_fear_index_value_color_shape
        FEAR -> R.drawable.fear_index_value_color_shape
        NEUTRAL -> R.drawable.neutral_index_value_color_shape
        GREED -> R.drawable.greed_index_value_color_shape
        EXTREME_GREED -> R.drawable.extreme_greed_index_value_color_shape
        else -> 0
    }
}