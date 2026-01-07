package com.mordva.data.mapper

import com.mordva.domain.model.local.MoviePackage
import com.mordva.domain.model.local.PackageParams
import com.mordva.domain.model.movie.Movie
import com.mordva.sqlite.entities.blocked.BlockedDetails
import com.mordva.sqlite.entities.blocked.BlockedEntity

internal fun BlockedDetails.toDomain() = MoviePackage(
    movie = movieDetails.toMovie(),
    date = blockedEntity.date
)

internal fun List<BlockedDetails>.toDomain() = map { it.toDomain() }

internal fun PackageParams.toBlockedEntity() = BlockedEntity(
    movieId = id,
    date = date
)

internal fun BlockedEntity.toDomain() = MoviePackage(
    movie = Movie(),
    date = date
)