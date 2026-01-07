package com.mordva.sqlite.entities.favorite_package

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.entities.movie.MovieDetails
import com.mordva.sqlite.entities.movie.entity.MovieEntity

data class FavoritePackageDetails(
    @Embedded val favoritePackageEntity: FavoritePackageEntity,

    @Relation(
        entity = MovieEntity::class,
        entityColumn = "id",
        parentColumn = "movie_id"
    )
    val movie: MovieDetails
)