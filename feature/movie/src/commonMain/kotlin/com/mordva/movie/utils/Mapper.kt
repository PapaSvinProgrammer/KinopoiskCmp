package com.mordva.movie.utils

import com.mordva.domain.model.category.WatchabilityItem
import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.local.RatedMovie
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.movie.Watchability
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.domain.model.PosterScreenObject
import com.mordva.movie.domain.model.WatchabilityItemScreenObject
import com.mordva.movie.domain.model.WatchabilityScreenObject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun Watchability.toScreenObject() = WatchabilityScreenObject(items.map { it.toDto() })
internal fun WatchabilityItem.toDto() = WatchabilityItemScreenObject(name, logo?.toDto(), url)
internal fun Poster.toDto() = PosterScreenObject(url, previewUrl)

fun List<PersonMovie>.toScreenObject() = map { it.toScreenObject() }
internal fun PersonMovie.toScreenObject() = PersonMovieScreenObject(
    id = id,
    description = description,
    enProfession = enProfession,
    profession = profession
)

@OptIn(ExperimentalTime::class)
internal fun Movie.toRatedMovie(rating: Int): RatedMovie {
    return RatedMovie(
        movie = this,
        rating = rating,
        dateRating = Clock.System.now().toEpochMilliseconds()
    )
}
