package com.mordva.data.mapper

import com.mordva.domain.model.local.MoviePackage
import com.mordva.domain.model.local.PackageParams
import com.mordva.domain.model.movie.Movie
import com.mordva.sqlite.entities.will_watch_package.WillWatchPackageDetails
import com.mordva.sqlite.entities.will_watch_package.WillWatchPackageEntity

internal fun WillWatchPackageDetails.toDomain() = MoviePackage(
    movie = movie.toMovie(),
    date = willWatchPackageEntity.date
)

internal fun List<WillWatchPackageDetails>.toDomain() = map { it.toDomain() }

internal fun PackageParams.toWillWatchPackageEntity() = WillWatchPackageEntity(
    movieId = id,
    date = date
)

internal fun WillWatchPackageEntity.toDomain() = MoviePackage(
    movie = Movie(),
    date = date
)