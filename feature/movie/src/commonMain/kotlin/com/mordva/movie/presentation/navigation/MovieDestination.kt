package com.mordva.movie.presentation.navigation

import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.domain.model.WatchabilityScreenObject
import kotlinx.serialization.Serializable

@Serializable
internal data class WatchabilityListRoute(
    val watchability: WatchabilityScreenObject
)

@Serializable
internal data class GroupPersonRoute(
    val persons: List<PersonMovieScreenObject>
)