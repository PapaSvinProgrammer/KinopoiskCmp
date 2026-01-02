package com.mordva.network.external.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class AudienceDto(
    val count: Int?,
    val country: String?,
)