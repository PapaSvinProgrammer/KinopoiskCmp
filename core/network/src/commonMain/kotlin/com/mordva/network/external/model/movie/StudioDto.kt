package com.mordva.network.external.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class StudioDto(
    val id: String?,
    val subType: String?,
    val title: String?,
    val type: String?,
    val movie: List<MovieDto>,
    val createdAt: String?,
    val updatedAt: String?
)