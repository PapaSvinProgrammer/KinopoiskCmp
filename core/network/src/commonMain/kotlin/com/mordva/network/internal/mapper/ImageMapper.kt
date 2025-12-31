package com.mordva.network.internal.mapper

import com.mordva.model.image.CollectionMovie
import com.mordva.network.internal.model.image.CollectionDto
import com.mordva.model.image.Poster
import com.mordva.network.internal.model.image.PosterDto

internal fun CollectionDto.toDomain(): CollectionMovie {
    return CollectionMovie(
        slug = slug,
        category = category,
        cover = cover?.toDomain(),
        moviesCount = moviesCount,
        name = name
    )
}

internal fun PosterDto.toDomain(): Poster {
    return Poster(
        id = id,
        height = height,
        width = width,
        url = url,
        previewUrl = previewUrl
    )
}