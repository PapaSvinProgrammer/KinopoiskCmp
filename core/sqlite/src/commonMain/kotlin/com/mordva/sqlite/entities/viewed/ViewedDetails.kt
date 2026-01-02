package com.mordva.sqlite.entities.viewed

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.entities.movie.MovieDetails
import com.mordva.sqlite.entities.movie.entity.MovieEntity

data class ViewedDetails(
    @Embedded val viewedEntity: ViewedEntity,

    @Relation(
        entity = MovieEntity::class,
        entityColumn = "id",
        parentColumn = "movie_id"
    )
    val movieDetails: MovieDetails
)