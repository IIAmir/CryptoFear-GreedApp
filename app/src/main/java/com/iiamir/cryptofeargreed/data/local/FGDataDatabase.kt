package com.iiamir.cryptofeargreed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iiamir.cryptofeargreed.data.local.entities.FGEntity

@Database(
    entities = [FGEntity::class],
    version = 1
)
abstract class FGDataDatabase : RoomDatabase() {
    abstract val fgDataDao: FGDataDao
}