package com.mordva.sqlite.model

data class EpisodeLocalDto(
    val number: Int?,
    val name: String?,
    val enName: String?,
    val airDate: String?,
    val description: String?,
    val enDescription: String?,
    val still: PosterLocalDto?,
)