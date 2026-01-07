package com.mordva.network.external.model.person

import kotlinx.serialization.Serializable

@Serializable
data class AwardDto(
    val title: String?,
    val year: Int?
)