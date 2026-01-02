package com.mordva.data.mapper

import com.mordva.domain.model.local.MoviePackage
import com.mordva.domain.model.local.PackageParams
import com.mordva.domain.model.movie.Movie
import com.mordva.sqlite.entities.viewed.ViewedDetails
import com.mordva.sqlite.entities.viewed.ViewedEntity

internal fun ViewedDetails.toDomain() = MoviePackage(
    movie = movieDetails.toMovie(),
    date = viewedEntity.date
)

internal fun List<ViewedDetails>.toDomain() = map { it.toDomain() }

internal fun PackageParams.toViewedEntity() = ViewedEntity(
    movieId = id,
    date = date
)

internal fun ViewedEntity.toDomain() = MoviePackage(
    movie = Movie(),
    date = date
)