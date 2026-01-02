package com.mordva.network.external.model.image

import kotlinx.serialization.Serializable

@Serializable
data class PosterDto(
    val id: String?,
    val height: Int?,
    val width: Int?,
    val url: String?,
    val previewUrl: String?,
)