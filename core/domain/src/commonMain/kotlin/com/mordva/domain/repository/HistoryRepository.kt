package com.mordva.domain.repository

import com.mordva.domain.model.local.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun insert(history: History)
    suspend fun delete(id: Int)
    fun getAll(): Flow<List<History>>
}