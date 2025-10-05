package com.mordva.domain.usecase.movie

import com.mordva.domain.repository.MovieRepository
import com.mordva.model.movie.Movie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class GetMovieById(
    private val movieRepository: MovieRepository
) : UseCase<Int, Result<Movie>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Movie> {
        return movieRepository.getMovieById(params)
    }
}
