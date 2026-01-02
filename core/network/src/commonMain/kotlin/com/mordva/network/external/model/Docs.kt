package com.mordva.network.external.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Docs <T> (
    val docs: List<T> = listOf(),
    val total: Int = 0
)