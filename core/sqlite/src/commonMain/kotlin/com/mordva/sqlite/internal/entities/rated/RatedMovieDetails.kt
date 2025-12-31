package com.mordva.sqlite.internal.entities.rated

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.internal.entities.movie.MovieDetails
import com.mordva.sqlite.internal.entities.movie.entity.MovieEntity

internal data class RatedMovieDetails(
    @Embedded val ratedMovie: RatedMovieEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id",
        entity = MovieEntity::class
    )
    val movieDetails: MovieDetails
)