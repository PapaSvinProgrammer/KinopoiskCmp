package com.mordva.movie.presentation.movie.widget

import com.mordva.domain.model.movie.Movie

internal sealed interface MovieState {
    data class Success(val data: Movie) : MovieState
    data object Loading : MovieState
    data object Error : MovieState
}