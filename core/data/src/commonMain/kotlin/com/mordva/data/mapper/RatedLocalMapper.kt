package com.mordva.data.mapper

import com.mordva.domain.model.local.RatedMovie
import com.mordva.domain.model.movie.Movie
import com.mordva.sqlite.entities.rated.RatedMovieDetails
import com.mordva.sqlite.entities.rated.RatedMovieEntity

internal fun RatedMovieDetails.toDomain(): RatedMovie {
    return RatedMovie(
        movie = movieDetails.toMovie(),
        rating = ratedMovie.rating,
        dateRating = ratedMovie.date
    )
}

internal fun RatedMovie.toWillWatchPackageEntity(): RatedMovieEntity {
    return RatedMovieEntity(
        movieId = movie.id,
        rating = rating,
        date = dateRating
    )
}

internal fun List<RatedMovieDetails>.toDomain() = map { it.toDomain() }

internal fun RatedMovieEntity.toDomain() = RatedMovie(
    movie = Movie(),
    rating = rating,
    dateRating = date
)