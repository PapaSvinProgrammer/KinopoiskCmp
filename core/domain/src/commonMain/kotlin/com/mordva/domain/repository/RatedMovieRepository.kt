package com.mordva.domain.repository

import com.mordva.domain.model.local.RatedMovie
import kotlinx.coroutines.flow.Flow

interface RatedMovieRepository {
    suspend fun add(ratedMovie: RatedMovie)
    suspend fun delete(movieId: Int)
    fun isStock(movieId: Int): Flow<RatedMovie?>
    fun getAll(): Flow<List<RatedMovie>>
    fun getAllByRating(rating: Int): Flow<List<RatedMovie>>
}
