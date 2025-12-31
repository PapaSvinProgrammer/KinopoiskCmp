package com.mordva.data

import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.model.local.RatedMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class RatedMovieRepositoryImpl(
//    private val service: RatedMovieService
) : RatedMovieRepository {
    override suspend fun add(ratedMovie: RatedMovie) {
//        service.insert(ratedMovie)
    }

    override suspend fun delete(movieId: Int) {
//        service.delete(movieId)
    }

    override fun isStock(movieId: Int): Flow<RatedMovie?> {
        return flowOf()
    }

    override fun getAll(): Flow<List<RatedMovie>> {
        return flowOf()
    }

    override fun getAllByRating(rating: Int): Flow<List<RatedMovie>> {
        return flowOf()
    }
}
