package com.mordva.network.external.model.season

import com.mordva.network.external.model.image.PosterDto
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val number: Int?,
    val name: String?,
    val enName: String?,
    val airDate: String?,
    val description: String?,
    val enDescription: String?,
    val still: PosterDto?,
)