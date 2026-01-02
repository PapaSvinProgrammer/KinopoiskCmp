package com.mordva.network.external

import com.mordva.network.external.model.movie.MovieDto

interface MovieService {
    suspend fun getMovieById(movieId: Int): Result<MovieDto>
    suspend fun searchMovieByName(page: Int, q: String): Result<List<MovieDto>>

    suspend fun getMoviesByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<MovieDto>>
}