package com.mordva.sqlite.utils

import com.mordva.sqlite.entities.movie.entity.AudienceEntity
import com.mordva.sqlite.entities.movie.entity.BudgetEntity
import com.mordva.sqlite.entities.movie.entity.CountryEntity
import com.mordva.sqlite.entities.movie.entity.DistributorEntity
import com.mordva.sqlite.entities.movie.entity.EpisodeEntity
import com.mordva.sqlite.entities.movie.entity.FactEntity
import com.mordva.sqlite.entities.movie.entity.GenreEntity
import com.mordva.sqlite.entities.movie.entity.MovieEntity
import com.mordva.sqlite.entities.movie.entity.PersonMovieEntity
import com.mordva.sqlite.entities.movie.entity.PosterEntity
import com.mordva.sqlite.entities.movie.entity.PremiereEntity
import com.mordva.sqlite.entities.movie.entity.RatingEntity
import com.mordva.sqlite.entities.movie.entity.ReleaseYearsEntity
import com.mordva.sqlite.entities.movie.entity.SeasonEntity
import com.mordva.sqlite.entities.movie.entity.VotesEntity
import com.mordva.sqlite.entities.movie.entity.WatchabilityItemEntity
import com.mordva.sqlite.model.MovieLocalDto
import com.mordva.sqlite.model.ItemNameLocalDto
import com.mordva.sqlite.model.WatchabilityItemLocalDto
import com.mordva.sqlite.model.PosterLocalDto
import com.mordva.sqlite.model.DistributorsLocalDto
import com.mordva.sqlite.model.FactLocalDto
import com.mordva.sqlite.model.PersonMovieLocalDto
import com.mordva.sqlite.model.SeasonLocalDto
import com.mordva.sqlite.model.AudienceLocalDto
import com.mordva.sqlite.model.BudgetLocalDto
import com.mordva.sqlite.model.PremiereLocalDto
import com.mordva.sqlite.model.RatingLocalDto
import com.mordva.sqlite.model.ReleaseYearsLocalDto
import com.mordva.sqlite.model.VotesLocalDto
import kotlin.jvm.JvmName

internal fun MovieLocalDto.toMovieEntity() = MovieEntity(
    id = id,
    type = type,
    name = name,
    description = description,
    slogan = slogan,
    year = year,
    alternativeName = alternativeName,
    movieLength = movieLength,
    status = status,
    ageRating = ageRating,
    ratingMpaa = ratingMpaa,
    updatedAt = updatedAt,
    shortDescription = shortDescription,
    top10 = top10,
    top250 = top250,
    isSeries = isSeries,
    seriesLength = seriesLength,
    totalSeriesLength = totalSeriesLength
)

internal fun RatingLocalDto?.toEntity(movieId: Int) = this?.let {
    listOf(
        RatingEntity(
            movieId = movieId,
            kp = it.kp,
            imdb = it.imdb,
            filmCritics = it.filmCritics,
            russianFilmCritics = it.russianFilmCritics
        )
    )
} ?: emptyList()

internal fun VotesLocalDto?.toEntity(movieId: Int) = this?.let {
    listOf(
        VotesEntity(
            movieId = movieId,
            kp = it.kp,
            imdb = it.imdb,
            filmCritics = it.filmCritics,
            russianFilmCritics = it.russianFilmCritics,
            await = it.await
        )
    )
} ?: emptyList()

internal fun DistributorsLocalDto?.toEntity(movieId: Int) = this?.let {
    listOf(
        DistributorEntity(
            movieId = movieId,
            distributor = it.distributor,
            distributorRelease = it.distributorRelease
        )
    )
} ?: emptyList()

internal fun PremiereLocalDto?.toEntity(movieId: Int) = this?.let {
    listOf(
        PremiereEntity(
            movieId = movieId,
            bluray = it.bluray,
            digital = it.digital,
            dvd = it.dvd,
            russia = it.russia,
            world = it.world
        )
    )
} ?: emptyList()

internal fun BudgetLocalDto?.toEntity(movieId: Int) = this?.let {
    listOf(
        BudgetEntity(
            movieId = movieId,
            value = it.value,
            currency = it.currency
        )
    )
} ?: emptyList()

internal fun PosterLocalDto?.toEntity(movieId: Int, type: String) = this?.let {
    listOf(
        PosterEntity(
            movieId = movieId,
            uid = id,
            height = height,
            width = width,
            type = type,
            url = it.url,
            previewUrl = it.previewUrl
        )
    )
} ?: emptyList()

@JvmName("listFactToEntities")
internal fun List<FactLocalDto>.toEntities(movieId: Int) = map {
    FactEntity(
        movieId = movieId,
        value = it.value,
        type = it.type,
        spoiler = it.spoiler
    )
}

@JvmName("listItemNameToGenreEntities")
internal fun List<ItemNameLocalDto>.toGenreEntities(movieId: Int) = map {
    GenreEntity(movieId = movieId, name = it.name, slug = it.slug)
}

@JvmName("listItemNameToCountryEntities")
internal fun List<ItemNameLocalDto>.toCountryEntities(movieId: Int) = map {
    CountryEntity(movieId = movieId, name = it.name, slug = it.slug)
}

@JvmName("listPersonMovieToEntities")
internal fun List<PersonMovieLocalDto>.toEntities(movieId: Int) = map {
    PersonMovieEntity(
        id = it.id,
        movieId = movieId,
        name = it.name,
        enName = it.enName,
        photo = it.photo,
        description = it.description,
        profession = it.profession,
        enProfession = it.enProfession
    )
}

@JvmName("listWatchabilityItemToEntities")
internal fun List<WatchabilityItemLocalDto>.toEntities(movieId: Int) = map {
    WatchabilityItemEntity(
        movieId = movieId,
        name = it.name,
        url = it.url
    )
}

@JvmName("listAudienceToEntities")
internal fun List<AudienceLocalDto>.toEntities(movieId: Int) = map {
    AudienceEntity(
        movieId = movieId,
        count = it.count,
        country = it.country
    )
}

@JvmName("listReleaseYears")
internal fun List<ReleaseYearsLocalDto>.toEntities(movieId: Int) = map {
    ReleaseYearsEntity(
        movieId = movieId,
        start = it.start,
        end = it.end
    )
}

@JvmName("listSeasonToSeasonEntities")
internal fun List<SeasonLocalDto>.toSeasonEntities() = map {
    SeasonEntity(
        movieId = it.movieId,
        number = it.number,
        name = it.name,
        enName = it.enName,
        episodesCount = it.episodesCount,
        airDate = it.airDate
    )
}

internal fun List<SeasonLocalDto>.toEpisodeEntities(seasons: List<SeasonEntity>): List<EpisodeEntity> {
    val result = mutableListOf<EpisodeEntity>()
    for ((index, season) in this.withIndex()) {
        val seasonId = seasons.getOrNull(index)?.seasonId ?: continue
        season.episodes.forEach { ep ->
            result.add(
                EpisodeEntity(
                    seasonOwnerId = seasonId,
                    number = ep.number ?: 0,
                    name = ep.name,
                    enName = ep.enName,
                    airDate = ep.airDate
                )
            )
        }
    }
    return result
}
