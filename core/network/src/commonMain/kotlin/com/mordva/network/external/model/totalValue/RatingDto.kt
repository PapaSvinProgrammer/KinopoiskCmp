package com.mordva.network.external.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    val kp: Float? = null,
    val imdb: Float? = null,
    val filmCritics: Float? = null,
    val russianFilmCritics: Float? = null
)