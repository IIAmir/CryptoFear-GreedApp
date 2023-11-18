package com.iiamir.cryptofeargreed.data.remote.dto

import com.iiamir.cryptofeargreed.data.local.entities.FGEntity
import com.iiamir.cryptofeargreed.data.util.timeStampToYearConverter
import com.iiamir.cryptofeargreed.utils.indexColor
import com.google.gson.annotations.SerializedName

data class FGDataModel(
    @SerializedName("time_until_update")
    val timeUntilUpdate: Int? = null,
    @SerializedName("timestamp")
    val timeStamp: String,
    @SerializedName("value")
    val indexValue: Int,
    @SerializedName("value_classification")
    val valueClassification: String,
    val valueClassificationColor: Long = 0
) {
    fun toFGEntityAndInsertConvertedData(): FGEntity {
        return FGEntity(
            timeStamp = timeStampToYearConverter(timeStamp.toInt()),
            indexValue = indexValue,
            valueClassification = valueClassification,
            valueClassificationColor = indexColor(valueClassification)
        )
    }
}