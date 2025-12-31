package com.mordva.sqlite.internal.entities.favorite_package

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.internal.entities.movie.MovieDetails
import com.mordva.sqlite.internal.entities.movie.entity.MovieEntity

internal data class FavoritePackageDetails(
    @Embedded val favoritePackageEntity: FavoritePackageEntity,

    @Relation(
        entity = MovieEntity::class,
        entityColumn = "id",
        parentColumn = "movie_id"
    )
    val movie: MovieDetails
)