package com.mordva.movie.presentation.randommovie.widget

import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.movie.Movie

internal sealed interface RandomMovieItemState {
    data class Success(
        val movie: Movie,
        val images: List<Poster>,
        val director: String,
    ) : RandomMovieItemState

    data object Loading : RandomMovieItemState
    data object Error : RandomMovieItemState

    companion object {
        fun fromData(
            movie: Movie,
            images: List<Poster>,
            directors: List<String>
        ): RandomMovieItemState = Success(
            movie = movie,
            images = images,
            director = directors.joinToString(", ")
        )
    }
}

internal fun RandomMovieItemState.Success.getGenres() = movie.genres.map { it.name }