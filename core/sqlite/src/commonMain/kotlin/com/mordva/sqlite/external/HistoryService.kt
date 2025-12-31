package com.mordva.sqlite.external

import com.mordva.model.local.History
import kotlinx.coroutines.flow.Flow

interface HistoryService {
    suspend fun insert(history: History)
    fun getAll(): Flow<List<History>>
    suspend fun delete(id: Int)
}