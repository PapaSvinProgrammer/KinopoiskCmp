package com.mordva.domain.repository

import com.mordva.model.History


interface HistoryRepository {
    suspend fun insert(history: History)
    suspend fun delete(id: Int)
    //fun getAll(): Flow<PagingData<History>>
}