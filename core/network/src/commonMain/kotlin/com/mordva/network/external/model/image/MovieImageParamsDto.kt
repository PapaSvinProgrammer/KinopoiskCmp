package com.mordva.network.external.model.image

data class MovieImageParamsDto(
    val movieId: Int,
    val page: Int = 1,
    val types: Set<ImageTypeKtor> = setOf()
)