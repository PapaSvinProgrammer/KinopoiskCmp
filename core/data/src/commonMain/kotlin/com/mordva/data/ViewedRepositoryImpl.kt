package com.mordva.data

import com.mordva.domain.repository.ViewedRepository
import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ViewedRepositoryImpl(
//    private val service: ViewedService
) : ViewedRepository {
    override suspend fun insert(params: PackageParams) {
//        service.insert(params)
    }

    override suspend fun delete(movieId: Int) {
//        service.delete(movieId)
    }

    override fun getByDateAsc(): Flow<List<MoviePackage>> {
        return flowOf()
    }

    override fun getByDateDesc(): Flow<List<MoviePackage>> {
        return flowOf()
    }

    override fun isStock(movieId: Int): Flow<MoviePackage?> {
        return flowOf()
    }

    override fun count(): Flow<Int> {
        return flowOf()
    }
}
