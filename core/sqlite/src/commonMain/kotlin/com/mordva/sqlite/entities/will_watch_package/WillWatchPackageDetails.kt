package com.mordva.sqlite.entities.will_watch_package

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.entities.movie.MovieDetails
import com.mordva.sqlite.entities.movie.entity.MovieEntity

data class WillWatchPackageDetails(
    @Embedded val willWatchPackageEntity: WillWatchPackageEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id",
        entity = MovieEntity::class
    )
    val movie: MovieDetails
)