package com.mordva.domain.model.local

import com.mordva.domain.model.movie.Movie

data class RatedMovie(
    val movie: Movie,
    val rating: Int,
    val dateRating: Long
)
