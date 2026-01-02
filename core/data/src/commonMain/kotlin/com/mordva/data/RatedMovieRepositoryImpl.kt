package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.data.mapper.toWillWatchPackageEntity
import com.mordva.domain.model.local.RatedMovie
import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.sqlite.entities.rated.RatedMovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RatedMovieRepositoryImpl(
    private val dao: RatedMovieDao
) : RatedMovieRepository {
    override suspend fun add(ratedMovie: RatedMovie) {
        dao.insert(ratedMovie.toWillWatchPackageEntity())
    }

    override suspend fun delete(movieId: Int) {
        dao.delete(movieId)
    }

    override fun isStock(movieId: Int): Flow<RatedMovie?> {
        return dao.isStock(movieId).map { it?.toDomain() }
    }

    override fun getAll(): Flow<List<RatedMovie>> {
        return dao.getAll().map { it.toDomain() }
    }

    override fun getAllByRating(rating: Int): Flow<List<RatedMovie>> {
        return dao.getAllByRating(rating).map { it.toDomain() }
    }
}
