package com.mordva.network.external.model.movie

import com.mordva.network.external.model.category.WatchabilityItemDto
import kotlinx.serialization.Serializable

@Serializable
data class WatchabilityDto(
    val items: List<WatchabilityItemDto>? = null
)