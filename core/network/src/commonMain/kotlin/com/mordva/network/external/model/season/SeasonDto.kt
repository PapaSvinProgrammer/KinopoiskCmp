package com.mordva.network.external.model.season

import kotlinx.serialization.Serializable

@Serializable
data class SeasonDto(
    val movieId: Int = 0,
    val number: Int = 0,
    val name: String? = null,
    val enName: String? = null,
    val episodesCount: Int? = null,
    val airDate: String? = null,
    val episodes: List<EpisodeDto> = listOf()
)