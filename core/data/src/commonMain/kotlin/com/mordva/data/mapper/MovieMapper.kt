package com.mordva.data.mapper

import com.mordva.domain.model.category.ItemName
import com.mordva.domain.model.category.WatchabilityItem
import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.movie.Comment
import com.mordva.domain.model.movie.Distributors
import com.mordva.domain.model.movie.Fact
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.movie.ShortMovie
import com.mordva.domain.model.movie.Studio
import com.mordva.domain.model.movie.Watchability
import com.mordva.domain.model.person.PersonMovie
import com.mordva.domain.model.season.Season
import com.mordva.domain.model.totalValue.Audience
import com.mordva.domain.model.totalValue.Budget
import com.mordva.domain.model.totalValue.Premiere
import com.mordva.domain.model.totalValue.Rating
import com.mordva.domain.model.totalValue.ReleaseYears
import com.mordva.domain.model.totalValue.Votes
import com.mordva.network.external.model.movie.CommentDto
import com.mordva.network.external.model.movie.FactDto
import com.mordva.network.external.model.movie.MovieDto
import com.mordva.network.external.model.movie.DistributorsDto
import com.mordva.network.external.model.movie.ShortMovieDto
import com.mordva.network.external.model.movie.StudioDto
import com.mordva.network.external.model.movie.WatchabilityDto
import com.mordva.sqlite.entities.movie.MovieDetails
import kotlin.jvm.JvmName

internal fun CommentDto.toDomain(): Comment = Comment(
    id = this.id,
    movieId = this.movieId,
    title = this.title,
    type = this.type,
    review = this.review,
    date = this.date,
    author = this.author,
    userRating = this.userRating,
    authorId = this.authorId,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    reviewDislikes = this.reviewDislikes,
    reviewLikes = this.reviewLikes
)

@JvmName("listCommentDtoToDomain")
internal fun List<CommentDto>.toDomain() = map { it.toDomain() }

internal fun DistributorsDto.toDomain(): Distributors = Distributors(
    distributor = this.distributor,
    distributorRelease = this.distributorRelease
)

@JvmName("listDistributorsDtoToDomain")
internal fun List<DistributorsDto>.toDomain() = map { it.toDomain() }

internal fun FactDto.toDomain(): Fact = Fact(
    value = this.value,
    type = this.type,
    spoiler = this.spoiler
)

@JvmName("listFactDtoToDomain")
internal fun List<FactDto>.toDomain() = map { it.toDomain() }

internal fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    type = type,
    name = name,
    rating = rating?.toDomain(),
    description = description,
    votes = votes?.toDomain(),
    distributors = distributors?.toDomain(),
    premiere = premiere?.toDomain(),
    slogan = slogan,
    year = year,
    budget = budget?.toDomain(),
    poster = poster?.toDomain(),
    facts = facts?.map { it.toDomain() } ?: listOf(),
    genres = genres.map { it.toDomain() },
    countries = countries.map { it.toDomain() },
    persons = persons.map { it.toDomain() },
    watchability = watchability.toDomain(),
    alternativeName = alternativeName,
    backdrop = backdrop?.toDomain(),
    movieLength = movieLength,
    status = status,
    ageRating = ageRating,
    ratingMpaa = ratingMpaa,
    updatedAt = updatedAt,
    shortDescription = shortDescription,
    similarMovies = similarMovies.map { it.toDomain() },
    sequelsAndPrequels = sequelsAndPrequels.map { it.toDomain() },
    logo = logo?.toDomain(),
    top10 = top10,
    top250 = top250,
    audience = audience.map { it.toDomain() },
    isSeries = isSeries,
    seriesLength = seriesLength,
    totalSeriesLength = totalSeriesLength,
    releaseYears = releaseYears.map { it.toDomain() },
    seasonsInfo = seasonsInfo?.map { it.toDomain() },
    lists = lists
)

@JvmName("listMovieDtoToDomain")
internal fun List<MovieDto>.toDomain() = map { it.toDomain() }

internal fun ShortMovieDto.toDomain(): ShortMovie = ShortMovie(
    id = this.id,
    name = this.name,
    alternativeName = this.alternativeName,
    rating = this.rating,
    description = this.description,
    enProfession = this.enProfession
)

@JvmName("listShortMovieDtoToDomain")
internal fun List<ShortMovieDto>.toDomain() = map { it.toDomain() }

internal fun StudioDto.toDomain(): Studio = Studio(
    id = this.id,
    subType = this.subType,
    title = this.title,
    type = this.type,
    movies = movie.map { it.toDomain() }
)

@JvmName("listStudioDtoToDomain")
internal fun List<StudioDto>.toDomain() = map { it.toDomain() }

internal fun WatchabilityDto.toDomain(): Watchability = Watchability(
    items = this.items?.map { it.toDomain() } ?: listOf()
)


internal fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = movie.id,
        type = movie.type,
        name = movie.name,
        description = movie.description,
        slogan = movie.slogan,
        year = movie.year,
        alternativeName = movie.alternativeName,
        movieLength = movie.movieLength,
        status = movie.status,
        ageRating = movie.ageRating,
        ratingMpaa = movie.ratingMpaa,
        updatedAt = movie.updatedAt,
        shortDescription = movie.shortDescription,
        top10 = movie.top10,
        top250 = movie.top250,
        isSeries = movie.isSeries,
        seriesLength = movie.seriesLength,
        totalSeriesLength = movie.totalSeriesLength,
        rating = ratings.firstOrNull()?.let {
            Rating(it.kp, it.imdb, it.filmCritics, it.russianFilmCritics)
        },
        votes = votes.firstOrNull()?.let {
            Votes(it.kp, it.imdb, it.filmCritics, it.russianFilmCritics, it.await)
        },
        distributors = distributors.firstOrNull()?.let {
            Distributors(it.distributor, it.distributorRelease)
        },
        premiere = premieres.firstOrNull()?.let {
            Premiere(it.bluray, it.digital, it.dvd, it.russia, it.world)
        },
        budget = budgets.firstOrNull()?.let {
            Budget(it.value, it.currency)
        },
        poster = posters.find { it.type == "poster" }?.let {
            Poster(it.uid, it.height, it.width, it.url, it.previewUrl)
        },
        backdrop = posters.find { it.type == "backdrop" }?.let {
            Poster(it.uid, it.height, it.width, it.url, it.previewUrl)
        },
        logo = posters.find { it.type == "logo" }?.let {
            Poster(it.uid, it.height, it.width, it.url, it.previewUrl)
        },
        facts = facts.map { Fact(it.value, it.type, it.spoiler) },
        genres = genres.map { ItemName(it.name, it.slug) },
        countries = countries.map { ItemName(it.name, it.slug) },
        persons = persons.map {
            PersonMovie(
                id = it.id,
                name = it.name,
                enName = it.enName,
                photo = it.photo,
                description = it.description,
                profession = it.profession,
                enProfession = it.enProfession
            )
        },
        watchability = Watchability(
            watchability.map { WatchabilityItem(it.name, null, it.url) }
        ),
        audience = audience.map { Audience(it.count, it.country) },
        releaseYears = releaseYears.map { ReleaseYears(it.start, it.end) },
        seasonsInfo = seasons.map {
            Season(
                movieId = it.movieId,
                number = it.number,
                name = it.name,
                enName = it.enName,
                episodesCount = it.episodesCount,
                airDate = it.airDate,
                episodes = emptyList()
            )
        }
    )
}
