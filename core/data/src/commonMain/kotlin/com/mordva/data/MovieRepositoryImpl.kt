package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.repository.MovieRepository
import com.mordva.network.external.MovieService

internal class MovieRepositoryImpl(
    private val service: MovieService
) : MovieRepository {
    override suspend fun getMovieByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>> {
        return service.getMoviesByFilter(queryParameters).map { it.toDomain() }
    }

    override suspend fun getMovieById(movieId: Int): Result<Movie> {
        return service.getMovieById(movieId).map { it.toDomain() }
    }

    override suspend fun search(
        q: String,
        page: Int
    ): Result<List<Movie>> {
        return service.searchMovieByName(
            page = page,
            q = q
        ).map { it.toDomain() }
    }
}