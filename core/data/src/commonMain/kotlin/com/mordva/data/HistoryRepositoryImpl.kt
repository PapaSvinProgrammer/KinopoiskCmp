package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.data.mapper.toWillWatchPackageEntity
import com.mordva.domain.model.local.History
import com.mordva.domain.repository.HistoryRepository
import com.mordva.sqlite.entities.history.HistoryDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class HistoryRepositoryImpl(
    private val dao: HistoryDao
) : HistoryRepository {
    override suspend fun insert(history: History) {
        dao.insert(history.toWillWatchPackageEntity())
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override fun getAll(): Flow<List<History>> {
        return dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }
}
