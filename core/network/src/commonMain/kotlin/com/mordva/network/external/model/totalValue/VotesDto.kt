package com.mordva.network.external.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class VotesDto(
    val kp: Int? = null,
    val imdb: Int? = null,
    val filmCritics: Int? = null,
    val russianFilmCritics: Int? = null,
    val await: Int? = null
)