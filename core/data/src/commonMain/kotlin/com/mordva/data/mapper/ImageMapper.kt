package com.mordva.data.mapper

import com.mordva.domain.model.image.CollectionMovie
import com.mordva.domain.model.image.ImageType
import com.mordva.domain.model.image.MovieImageParams
import com.mordva.domain.model.image.Poster
import com.mordva.network.external.model.image.CollectionDto
import com.mordva.network.external.model.image.ImageTypeKtor
import com.mordva.network.external.model.image.MovieImageParamsDto
import com.mordva.network.external.model.image.PosterDto
import kotlin.jvm.JvmName

internal fun CollectionDto.toDomain(): CollectionMovie {
    return CollectionMovie(
        slug = slug,
        category = category,
        cover = cover?.toDomain(),
        moviesCount = moviesCount,
        name = name
    )
}

@JvmName("listCollectionDtoToDomain")
internal fun List<CollectionDto>.toDomain() = map { it.toDomain() }

internal fun PosterDto.toDomain(): Poster {
    return Poster(
        id = id,
        height = height,
        width = width,
        url = url,
        previewUrl = previewUrl
    )
}

internal fun List<PosterDto>.toDomain() = map { it.toDomain() }

internal fun MovieImageParams.toDto() = MovieImageParamsDto(
    movieId = movieId,
    page = page,
    types = types.map { it.toDto() }.toSet(),
)

internal fun ImageType.toDto(): ImageTypeKtor = when (this) {
    ImageType.ALL -> ImageTypeKtor.ALL
    ImageType.BACKDROP -> ImageTypeKtor.BACKDROP
    ImageType.COVER -> ImageTypeKtor.COVER
    ImageType.FRAME -> ImageTypeKtor.FRAME
    ImageType.PROMO -> ImageTypeKtor.PROMO
    ImageType.SCREENSHOT -> ImageTypeKtor.SCREENSHOT
    ImageType.SHOOTING -> ImageTypeKtor.SHOOTING
    ImageType.STILL -> ImageTypeKtor.STILL
    ImageType.WALLPAPER -> ImageTypeKtor.WALLPAPER
}