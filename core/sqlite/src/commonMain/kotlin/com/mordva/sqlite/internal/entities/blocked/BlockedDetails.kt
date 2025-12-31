package com.mordva.sqlite.internal.entities.blocked

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.internal.entities.movie.MovieDetails
import com.mordva.sqlite.internal.entities.movie.entity.MovieEntity

internal data class BlockedDetails(
    @Embedded val blockedEntity: BlockedEntity,

    @Relation(
        entity = MovieEntity::class,
        parentColumn = "movie_id",
        entityColumn = "id"
    )
    val movieDetails: MovieDetails
)