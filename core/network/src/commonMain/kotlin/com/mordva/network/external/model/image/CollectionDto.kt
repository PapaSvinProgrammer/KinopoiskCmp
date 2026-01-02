package com.mordva.network.external.model.image

import kotlinx.serialization.Serializable

@Serializable
data class CollectionDto(
    val slug: String?,
    val category: String?,
    val cover: PosterDto?,
    val moviesCount: Int?,
    val name: String?
)