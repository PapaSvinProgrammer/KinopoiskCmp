package com.mordva.data.mapper

import com.mordva.domain.model.season.Episode
import com.mordva.domain.model.season.Season
import com.mordva.network.external.model.season.EpisodeDto
import com.mordva.network.external.model.season.SeasonDto

internal fun EpisodeDto.toDomain(): Episode = Episode(
    number = this.number,
    name = this.name,
    enName = this.enName,
    airDate = this.airDate,
    description = this.description,
    enDescription = this.enDescription,
    still = this.still?.toDomain()
)

internal fun SeasonDto.toDomain(): Season = Season(
    movieId = this.movieId,
    number = this.number,
    name = this.name,
    enName = this.enName,
    episodesCount = this.episodesCount,
    airDate = this.airDate,
    episodes = this.episodes.map { it.toDomain() }
)