package com.mordva.data

import com.mordva.domain.repository.HistoryRepository
import com.mordva.model.History

internal class HistoryRepositoryImpl(
    //private val service: HistoryService
) : HistoryRepository {
    override suspend fun insert(history: History) {
        //service.insert(history)
    }

    override suspend fun delete(id: Int) {
        //service.delete(id)
    }

//    override fun getAll(): Flow<PagingData<History>> {
//        return service.getAll()
//    }
}
