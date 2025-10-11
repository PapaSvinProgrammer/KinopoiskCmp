package com.mordva.movie.utils

import com.mordva.model.movie.Movie
import com.mordva.ui.uiState.MovieUIState

internal fun MovieUIState.body(): Movie {
    return (this as? MovieUIState.Success)?.data?.first() ?: Movie()
}