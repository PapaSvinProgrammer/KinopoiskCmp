package com.mordva.network.external.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class FactDto(
    val value: String,
    val type: String? = null,
    val spoiler: Boolean? = null,
)