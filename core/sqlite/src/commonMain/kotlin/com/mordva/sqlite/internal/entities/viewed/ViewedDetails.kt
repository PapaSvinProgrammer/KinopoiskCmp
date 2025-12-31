package com.mordva.sqlite.internal.entities.viewed

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.internal.entities.movie.MovieDetails
import com.mordva.sqlite.internal.entities.movie.entity.MovieEntity

internal data class ViewedDetails(
    @Embedded val viewedEntity: ViewedEntity,

    @Relation(
        entity = MovieEntity::class,
        entityColumn = "id",
        parentColumn = "movie_id"
    )
    val movieDetails: MovieDetails
)