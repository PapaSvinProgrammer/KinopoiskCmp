package com.mordva.network.external.model.category

import kotlinx.serialization.Serializable

@Serializable
data class ItemNameDto(
    val name: String = "",
    val slug: String = ""
)