package com.mordva.movie.utils

import com.mordva.domain.model.movie.Movie
import com.mordva.movie.presentation.movie.widget.MovieState

internal fun MovieState.body(): Movie {
    return (this as? MovieState.Success)?.data ?: Movie()
}