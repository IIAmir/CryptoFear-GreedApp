package com.iiamir.cryptofeargreed.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel

@Entity
data class FGEntity(
    @PrimaryKey(autoGenerate = false) val timeStamp: String,
    val indexValue: Int,
    val valueClassification: String,
    val valueClassificationColor: Long = 0
) {
    fun toFGDataModel(): FGDataModel = FGDataModel(
        timeStamp = timeStamp,
        indexValue = indexValue,
        valueClassification = valueClassification,
        valueClassificationColor = valueClassificationColor
    )
}