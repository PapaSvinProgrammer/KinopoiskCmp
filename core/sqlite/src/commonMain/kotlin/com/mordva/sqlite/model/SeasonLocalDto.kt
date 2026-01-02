package com.mordva.sqlite.model

data class SeasonLocalDto(
    val movieId: Int,
    val number: Int,
    val name: String?,
    val enName: String?,
    val episodesCount: Int?,
    val airDate: String?,
    val episodes: List<EpisodeLocalDto>
)