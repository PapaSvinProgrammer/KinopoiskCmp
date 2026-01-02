package com.mordva.sqlite.model

data class RatingLocalDto(
    val kp: Float? = null,
    val imdb: Float? = null,
    val filmCritics: Float? = null,
    val russianFilmCritics: Float? = null
)