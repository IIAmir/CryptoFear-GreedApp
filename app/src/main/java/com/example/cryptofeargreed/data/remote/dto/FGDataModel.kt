package com.example.cryptofeargreed.data.remote.dto

import com.example.cryptofeargreed.data.local.entities.FGEntity
import com.google.gson.annotations.SerializedName

var id: Int = 0

data class FGDataModel(
    @SerializedName("time_until_update")
    val timeUntilUpdate: Int? = 0,
    @SerializedName("timestamp")
    val timeStamp: Int,
    @SerializedName("value")
    val indexValue: Int,
    @SerializedName("value_classification")
    val valueClassification: String,
) {
    fun toFGEntity(): FGEntity {
        return FGEntity(
            id = id++,
            timeUntilUpdate = timeUntilUpdate,
            timeStamp = timeStamp,
            indexValue = indexValue,
            valueClassification = valueClassification
        )
    }
}