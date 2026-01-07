package com.mordva.sqlite.entities.blocked

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.entities.movie.MovieDetails
import com.mordva.sqlite.entities.movie.entity.MovieEntity

data class BlockedDetails(
    @Embedded val blockedEntity: BlockedEntity,

    @Relation(
        entity = MovieEntity::class,
        parentColumn = "movie_id",
        entityColumn = "id"
    )
    val movieDetails: MovieDetails
)