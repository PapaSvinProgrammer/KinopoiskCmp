package com.mordva.sqlite.internal.entities.rated

import com.mordva.model.local.RatedMovie
import com.mordva.sqlite.external.RatedMovieService
import com.mordva.sqlite.internal.utils.toDomain
import com.mordva.sqlite.internal.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RatedMovieServiceImpl(
    private val dao: RatedMovieDao
) : RatedMovieService {
    override suspend fun insert(movie: RatedMovie) {
        dao.insert(movie.toEntity())
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override fun isStock(id: Int): Flow<RatedMovie?> {
        return dao.isStock(id).map { it?.toDomain() }
    }

    override fun getAll(): Flow<List<RatedMovie>> {
        return dao.getAll().map { it.toDomain() }
    }

    override fun getAllByRating(rating: Int): Flow<List<RatedMovie>> {
        return dao.getAllByRating(rating).map { it.toDomain() }
    }
}
