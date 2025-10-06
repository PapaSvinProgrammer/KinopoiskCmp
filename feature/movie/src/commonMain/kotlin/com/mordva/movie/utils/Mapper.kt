package com.mordva.movie.utils

import com.mordva.model.category.WatchabilityItem
import com.mordva.model.image.Poster
import com.mordva.model.movie.Watchability
import com.mordva.model.person.PersonMovie
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.domain.model.PosterScreenObject
import com.mordva.movie.domain.model.WatchabilityItemScreenObject
import com.mordva.movie.domain.model.WatchabilityScreenObject

fun Watchability.toScreenObject() = WatchabilityScreenObject(items.map { it.toDto() })
internal fun WatchabilityItem.toDto() = WatchabilityItemScreenObject(name, logo?.toDto(), url)
internal fun Poster.toDto() = PosterScreenObject(url, previewUrl)

fun List<PersonMovie>.toScreenObject() = this.map { it.toScreenObject() }
internal fun PersonMovie.toScreenObject() = PersonMovieScreenObject(
    id = id,
    description = description,
    enProfession = enProfession,
    profession = profession
)