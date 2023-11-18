package com.iiamir.cryptofeargreed.utils

import com.iiamir.cryptofeargreed.utils.Constants.EXTREME_FEAR
import com.iiamir.cryptofeargreed.utils.Constants.EXTREME_FEAR_INDEX_VALUE_CODE
import com.iiamir.cryptofeargreed.utils.Constants.EXTREME_GREED
import com.iiamir.cryptofeargreed.utils.Constants.EXTREME_GREED_INDEX_VALUE_CODE
import com.iiamir.cryptofeargreed.utils.Constants.FEAR
import com.iiamir.cryptofeargreed.utils.Constants.FEAR_INDEX_VALUE_CODE
import com.iiamir.cryptofeargreed.utils.Constants.GREED
import com.iiamir.cryptofeargreed.utils.Constants.GREED_INDEX_VALUE_CODE
import com.iiamir.cryptofeargreed.utils.Constants.NEUTRAL
import com.iiamir.cryptofeargreed.utils.Constants.NEUTRAL_INDEX_VALUE_CODE

private val indexColorMap = mapOf(
    EXTREME_FEAR to EXTREME_FEAR_INDEX_VALUE_CODE,
    FEAR to FEAR_INDEX_VALUE_CODE,
    NEUTRAL to NEUTRAL_INDEX_VALUE_CODE,
    GREED to GREED_INDEX_VALUE_CODE,
    EXTREME_GREED to EXTREME_GREED_INDEX_VALUE_CODE
)

fun indexColor(valueClassification: String): Long {
    return indexColorMap[valueClassification] ?: 0L
}