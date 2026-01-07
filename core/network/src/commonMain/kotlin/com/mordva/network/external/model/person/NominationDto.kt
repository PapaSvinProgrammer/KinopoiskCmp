package com.mordva.network.external.model.person

import kotlinx.serialization.Serializable

@Serializable
data class NominationDto(
    val award: AwardDto?,
    val title: String?
)