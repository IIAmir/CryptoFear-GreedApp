package com.iiamir.cryptofeargreed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iiamir.cryptofeargreed.data.local.entities.FGEntity

@Dao
interface FGDataDao {

    @Query("SELECT * FROM fgentity")
    suspend fun getFGData(): List<FGEntity>

    @Query("SELECT * FROM fgentity")
    fun getFGDataForWidget(): List<FGEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFGData(data: List<FGEntity>)

    @Query("DELETE FROM fgentity")
    suspend fun deleteFGData()

    // Version 2

    @Query("SELECT * FROM fgentity WHERE LOWER(timeStamp) LIKE '%' || LOWER(:date) || '%' AND valueClassification LIKE '%' || :valueClassification || '%'")
    suspend fun searchFGData(
        date: String,
        valueClassification: String = ""
    ): List<FGEntity>

}