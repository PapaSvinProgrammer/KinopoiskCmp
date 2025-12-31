package com.mordva.movie.utils

import com.mordva.model.movie.Movie
import com.mordva.movie.presentation.movie.widget.MovieUIState

internal fun MovieUIState.body(): Movie {
    return (this as? MovieUIState.Success)?.data ?: Movie()
}