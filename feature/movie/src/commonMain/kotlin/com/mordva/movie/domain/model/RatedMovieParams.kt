package com.mordva.movie.domain.model

import com.mordva.domain.model.movie.Movie

internal class RatedMovieParams(
    val movie: Movie,
    val rating: Int
)