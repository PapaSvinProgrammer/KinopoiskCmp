package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.movie.Movie

@Immutable
sealed interface MovieListUIState {
    data class Success(val data: List<Movie>) : MovieListUIState
    data object Loading : MovieListUIState
}