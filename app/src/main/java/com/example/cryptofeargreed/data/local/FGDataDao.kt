package com.example.cryptofeargreed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptofeargreed.data.local.entities.FGEntity

@Dao
interface FGDataDao {

    @Query("SELECT * FROM fgentity")
    suspend fun getFGData(): List<FGEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFGData(data: List<FGEntity>)

    @Query("DELETE FROM fgentity")
    suspend fun deleteFGData()

}