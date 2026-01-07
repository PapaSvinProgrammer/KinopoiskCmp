package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.data.mapper.toDto
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.repository.MovieRepository
import com.mordva.network.external.MovieService
import com.mordva.sqlite.entities.movie.MovieLocalService

internal class MovieRepositoryImpl(
    private val remote: MovieService,
    private val local: MovieLocalService,
) : MovieRepository {
    override suspend fun getMovieByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>> {
        return remote.getMoviesByFilter(queryParameters).map { it.toDomain() }
    }

    override suspend fun getMovieById(movieId: Int): Result<Movie> {
        return remote.getMovieById(movieId).map { it.toDomain() }
    }

    override suspend fun search(
        q: String,
        page: Int
    ): Result<List<Movie>> {
        return remote.searchMovieByName(
            page = page,
            q = q
        ).map { it.toDomain() }
    }

    override suspend fun save(movie: Movie) {
        local.insert(movie.toDto())
    }
}