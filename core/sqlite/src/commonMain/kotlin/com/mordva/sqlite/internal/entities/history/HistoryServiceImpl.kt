package com.mordva.sqlite.internal.entities.history

import com.mordva.model.local.History
import com.mordva.sqlite.external.HistoryService
import com.mordva.sqlite.internal.utils.toDomain
import com.mordva.sqlite.internal.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class HistoryServiceImpl(
    private val dao: HistoryDao
) : HistoryService {
    override suspend fun insert(history: History) {
        return dao.insert(history.toEntity())
    }

    override fun getAll(): Flow<List<History>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun delete(id: Int) {
        return dao.delete(id)
    }
}
