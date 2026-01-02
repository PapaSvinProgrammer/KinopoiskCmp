package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.data.mapper.toFavoritePackageEntity
import com.mordva.domain.model.local.MoviePackage
import com.mordva.domain.model.local.PackageParams
import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.sqlite.entities.favorite_package.FavoritePackageDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoritePackageRepositoryImpl(
    private val dao: FavoritePackageDao
) : FavoritePackageRepository {
    override suspend fun insert(params: PackageParams) {
        dao.insert(params.toFavoritePackageEntity())
    }

    override suspend fun delete(movieId: Int) {
        dao.delete(movieId)
    }

    override fun getByDateAsc(): Flow<List<MoviePackage>> {
        return dao.getSortByDateAsc().map { it.toDomain() }
    }

    override fun getByDateDesc(): Flow<List<MoviePackage>> {
        return dao.getSortByDateDesc().map { it.toDomain() }
    }

    override fun isStock(movieId: Int): Flow<MoviePackage?> {
        return dao.isStock(movieId).map { it?.toDomain() }
    }

    override fun count(): Flow<Int> {
        return dao.count()
    }
}
