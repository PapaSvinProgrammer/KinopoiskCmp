package com.mordva.network.external.model.category

import com.mordva.network.external.model.image.PosterDto
import kotlinx.serialization.Serializable

@Serializable
data class WatchabilityItemDto(
    val name: String? = null,
    val logo: PosterDto? = null,
    val url: String? = null,
)