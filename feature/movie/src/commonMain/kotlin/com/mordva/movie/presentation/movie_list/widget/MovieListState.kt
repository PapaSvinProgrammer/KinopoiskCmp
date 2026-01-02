package com.mordva.movie.presentation.movie_list.widget

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.movie.Movie

@Immutable
internal sealed interface MovieListState {
    data class Success(val data: List<Movie>) : MovieListState
    data object Loading : MovieListState
}