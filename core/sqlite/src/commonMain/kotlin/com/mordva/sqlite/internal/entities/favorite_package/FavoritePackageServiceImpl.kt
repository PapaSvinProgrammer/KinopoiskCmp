package com.mordva.sqlite.internal.entities.favorite_package

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.sqlite.external.FavoritePackageService
import com.mordva.sqlite.internal.utils.toDomain
import com.mordva.sqlite.internal.utils.toFavoritePackageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoritePackageServiceImpl(
    private val dao: FavoritePackageDao
): FavoritePackageService{
    override suspend fun insert(params: PackageParams) {
        dao.insert(params.toFavoritePackageEntity())
    }

    override suspend fun delete(movieId: Int) {
        dao.delete(movieId)
    }

    override fun getByDateDesc(): Flow<List<MoviePackage>> {
        return dao.getSortByDateDesc().map { it.toDomain() }
    }

    override fun getByDateAsc(): Flow<List<MoviePackage>> {
        return dao.getSortByDateAsc().map { it.toDomain() }
    }

    override fun isStock(movieId: Int): Flow<MoviePackage?> {
        return dao.isStock(movieId).map { it?.toDomain() }
    }

    override fun count(): Flow<Int> {
        return dao.count()
    }
}