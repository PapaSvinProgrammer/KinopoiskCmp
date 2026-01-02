package com.mordva.sqlite.entities.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAll(): Flow<List<HistoryEntity>>

    @Query("DELETE FROM search_history WHERE movie_id = :id")
    suspend fun delete(id: Int)
}
