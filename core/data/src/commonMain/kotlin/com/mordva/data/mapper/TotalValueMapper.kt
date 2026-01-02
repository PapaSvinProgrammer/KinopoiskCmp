package com.mordva.data.mapper

import com.mordva.domain.model.totalValue.Audience
import com.mordva.domain.model.totalValue.Budget
import com.mordva.domain.model.totalValue.Premiere
import com.mordva.domain.model.totalValue.Rating
import com.mordva.domain.model.totalValue.ReleaseYears
import com.mordva.domain.model.totalValue.Votes
import com.mordva.network.external.model.totalValue.AudienceDto
import com.mordva.network.external.model.totalValue.BudgetDto
import com.mordva.network.external.model.totalValue.PremiereDto
import com.mordva.network.external.model.totalValue.RatingDto
import com.mordva.network.external.model.totalValue.ReleaseYearsDto
import com.mordva.network.external.model.totalValue.VotesDto

internal fun AudienceDto.toDomain(): Audience = Audience(
    count = this.count,
    country = this.country
)

internal fun BudgetDto.toDomain(): Budget = Budget(
    value = this.value,
    currency = this.currency
)

internal fun PremiereDto.toDomain(): Premiere = Premiere(
    bluray = bluray,
    digital = digital,
    dvd = dvd,
    russia = russia,
    world = world,
)

internal fun RatingDto.toDomain(): Rating = Rating(
    kp = this.kp,
    imdb = this.imdb,
    filmCritics = this.filmCritics,
    russianFilmCritics = this.russianFilmCritics
)

internal fun ReleaseYearsDto.toDomain(): ReleaseYears = ReleaseYears(
    start = this.start,
    end = this.end
)

internal fun VotesDto.toDomain(): Votes = Votes(
    kp = this.kp,
    imdb = this.imdb,
    filmCritics = this.filmCritics,
    russianFilmCritics = this.russianFilmCritics,
    await = this.await
)
