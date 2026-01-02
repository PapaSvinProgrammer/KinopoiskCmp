package com.mordva.sqlite.entities.rated

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.entities.movie.MovieDetails
import com.mordva.sqlite.entities.movie.entity.MovieEntity

data class RatedMovieDetails(
    @Embedded val ratedMovie: RatedMovieEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id",
        entity = MovieEntity::class
    )
    val movieDetails: MovieDetails
)