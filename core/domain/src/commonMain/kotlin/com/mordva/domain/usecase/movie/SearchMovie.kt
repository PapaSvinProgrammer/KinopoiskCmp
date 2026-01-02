package com.mordva.domain.usecase.movie

import com.mordva.domain.model.movie.Movie
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.movie.model.MovieParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class SearchMovie(
    private val movieRepository: MovieRepository
) : UseCase<MovieParams, Result<List<Movie>>>(Dispatchers.IO) {
    override suspend fun run(params: MovieParams): Result<List<Movie>> {
        if (params.q.length < 3 || params.page <= 0) {
            return Result.failure(Exception())
        }

        return movieRepository.search(
            q = params.q,
            page = params.page
        )
    }
}
