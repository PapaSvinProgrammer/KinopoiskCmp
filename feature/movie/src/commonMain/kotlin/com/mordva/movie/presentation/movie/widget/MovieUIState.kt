package com.mordva.movie.presentation.movie.widget

import com.mordva.model.movie.Movie

internal sealed interface MovieUIState {
    data class Success(val data: Movie) : MovieUIState
    data object Loading : MovieUIState
    data object Error : MovieUIState
}