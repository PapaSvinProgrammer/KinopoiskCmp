package com.mordva.network.external.model.person

import com.mordva.network.external.model.movie.ShortMovieDto
import kotlinx.serialization.Serializable

@Serializable
data class NominationAwardDto(
    val nomination: NominationDto?,
    val winning: Boolean?,
    val personId: Int?,
    val movie: ShortMovieDto?
)
