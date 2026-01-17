package com.mordva.movie.domain.movie

import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.movie.MovieFilter
import com.mordva.domain.repository.ImageRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.movie.presentation.randommovie.widget.RandomMovieItemState
import com.mordva.util.multiRequestWithResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

internal class RandomMovieLoadItemsUseCase(
    private val movieRepository: MovieRepository,
    private val imageRepository: ImageRepository,
) {

    suspend fun execute(count: Int): List<RandomMovieItemState> {
        val movieResults: List<Result<Movie>> = multiRequestWithResult(count) {
            movieRepository.getRandomMovie(MovieFilter())
        }

        if (movieResults.all { it.isFailure }) {
            return List(count) { RandomMovieItemState.Error }
        }

        val successfulMovies = movieResults.mapNotNull { it.getOrNull() }

        val (imagesResults, directorsResults) = coroutineScope {
            val images = multiRequestWithResult(successfulMovies) { movie ->
                imageRepository.getMovieImages(movie.id)
            }

            val directors = multiRequestWithResult(successfulMovies) { movie ->
                getMovieDirectors(movie)
            }

            images.map { it.getOrDefault(listOf()) } to directors.map { it.getOrDefault(listOf()) }
        }

        return mergeData(movieResults, imagesResults, directorsResults)
    }

    private suspend fun getMovieDirectors(
        movie: Movie
    ): Result<List<String>> = withContext(Dispatchers.Default) {
        val directors = movie.persons
            .filter { it.enProfession == "director" }
            .mapNotNull { it.name?.takeIf { name -> name.isNotBlank() } }

        when {
            directors.isEmpty() -> Result.failure(Exception("Director not found"))
            else -> Result.success(directors)
        }
    }

    private fun mergeData(
        movies: List<Result<Movie>>,
        images: List<List<Poster>>,
        director: List<List<String>>
    ): List<RandomMovieItemState> {
        return movies.mapIndexed { index, movie ->
            movie.fold(
                onSuccess = {
                    RandomMovieItemState.fromData(
                        movie = it,
                        images = images.getOrNull(index) ?: listOf(),
                        directors = director.getOrNull(index) ?: listOf()
                    )
                },
                onFailure = { RandomMovieItemState.Error }
            )
        }
    }
}
