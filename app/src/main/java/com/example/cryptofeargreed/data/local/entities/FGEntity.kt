package com.example.cryptofeargreed.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptofeargreed.data.remote.dto.FGDataModel

@Entity
data class FGEntity(
    @PrimaryKey val id: Int,
    val timeUntilUpdate: Int? = 0,
    val timeStamp: Int,
    val indexValue: Int,
    val valueClassification: String,
) {
    fun toFGDataModel(): FGDataModel {
        return FGDataModel(
            timeUntilUpdate = timeUntilUpdate,
            timeStamp = timeStamp,
            indexValue = indexValue,
            valueClassification = valueClassification
        )
    }
}