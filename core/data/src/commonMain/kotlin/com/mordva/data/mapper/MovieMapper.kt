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
import com.mordva.domain.model.season.Episode
import com.mordva.domain.model.season.Season
import com.mordva.domain.model.totalValue.Audience
import com.mordva.domain.model.totalValue.Budget
import com.mordva.domain.model.totalValue.Premiere
import com.mordva.domain.model.totalValue.Rating
import com.mordva.domain.model.totalValue.ReleaseYears
import com.mordva.domain.model.totalValue.Votes
import com.mordva.network.external.model.movie.CommentDto
import com.mordva.network.external.model.movie.DistributorsDto
import com.mordva.network.external.model.movie.FactDto
import com.mordva.network.external.model.movie.MovieDto
import com.mordva.network.external.model.movie.ShortMovieDto
import com.mordva.network.external.model.movie.StudioDto
import com.mordva.network.external.model.movie.WatchabilityDto
import com.mordva.sqlite.entities.movie.MovieDetails
import com.mordva.sqlite.model.AudienceLocalDto
import com.mordva.sqlite.model.BudgetLocalDto
import com.mordva.sqlite.model.DistributorsLocalDto
import com.mordva.sqlite.model.EpisodeLocalDto
import com.mordva.sqlite.model.FactLocalDto
import com.mordva.sqlite.model.ItemNameLocalDto
import com.mordva.sqlite.model.MovieLocalDto
import com.mordva.sqlite.model.PersonMovieLocalDto
import com.mordva.sqlite.model.PosterLocalDto
import com.mordva.sqlite.model.PremiereLocalDto
import com.mordva.sqlite.model.RatingLocalDto
import com.mordva.sqlite.model.ReleaseYearsLocalDto
import com.mordva.sqlite.model.SeasonLocalDto
import com.mordva.sqlite.model.VotesLocalDto
import com.mordva.sqlite.model.WatchabilityItemLocalDto
import com.mordva.sqlite.model.WatchabilityLocalDto
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

internal fun Movie.toDto(): MovieLocalDto = MovieLocalDto(
    id = id,
    type = type,
    name = name,
    rating = rating?.toDto(),
    description = description,
    votes = votes?.toDto(),
    distributors = distributors?.toDto(),
    premiere = premiere?.toDto(),
    slogan = slogan,
    year = year,
    budget = budget?.toDto(),
    poster = poster?.toDto(),
    facts = facts.toDto(),
    genres = genres.toDto(),
    countries = countries.toDto(),
    persons = persons.toDto(),
    watchability = watchability.toDto(),
    alternativeName = alternativeName,
    backdrop = backdrop?.toDto(),
    movieLength = movieLength,
    status = status,
    ageRating = ageRating,
    ratingMpaa = ratingMpaa,
    updatedAt = updatedAt,
    shortDescription = shortDescription,
    similarMovies = similarMovies.map { it.toDto() },
    sequelsAndPrequels = sequelsAndPrequels.map { it.toDto() },
    logo = logo?.toDto(),
    top10 = top10,
    top250 = top250,
    audience = audience.toDto(),
    isSeries = isSeries,
    seriesLength = seriesLength,
    totalSeriesLength = totalSeriesLength,
    releaseYears = releaseYears.toDto(),
    seasonsInfo = seasonsInfo?.toDto(),
    lists = lists
)

internal fun Rating.toDto() = RatingLocalDto(
    kp = kp,
    imdb = imdb,
    filmCritics = filmCritics,
    russianFilmCritics = russianFilmCritics,
)

internal fun Votes.toDto() = VotesLocalDto(
    kp = kp,
    imdb = imdb,
    filmCritics = filmCritics,
    russianFilmCritics = russianFilmCritics,
)

internal fun Distributors.toDto() = DistributorsLocalDto(
    distributor = distributor,
    distributorRelease = distributorRelease,
)

internal fun Premiere.toDto() = PremiereLocalDto(
    bluray = bluray,
    digital = digital,
    dvd = dvd,
    russia = russia,
    world = world
)

internal fun Budget.toDto() = BudgetLocalDto(
    value = value,
    currency = currency
)

internal fun Poster.toDto() = PosterLocalDto(
    id = id,
    height = height,
    width = width,
    url = url,
    previewUrl = previewUrl
)

@JvmName("listFactToDto")
internal fun List<Fact>.toDto() = map {
    FactLocalDto(
        value = it.value,
        type = it.type,
        spoiler = it.spoiler
    )
}

@JvmName("listItemNameToDto")
internal fun List<ItemName>.toDto() = map {
    ItemNameLocalDto(
        name = it.name,
        slug = it.slug
    )
}

@JvmName("listReleaseYearsToDto")
internal fun List<ReleaseYears>.toDto() = map {
    ReleaseYearsLocalDto(
        start = it.start,
        end = it.end
    )
}

@JvmName("listSeasonToDto")
internal fun List<Season>.toDto() = map {
    SeasonLocalDto(
        movieId = it.movieId,
        number = it.number,
        name = it.name,
        enName = it.enName,
        episodesCount = it.episodesCount,
        airDate = it.airDate,
        episodes = it.episodes.toDto()
    )
}

@JvmName("listEpisodeToDto")
internal fun List<Episode>.toDto() = map {
    EpisodeLocalDto(
        number = it.number,
        name = it.name,
        enName = it.enName,
        airDate = it.airDate,
        description = it.description,
        enDescription = it.enDescription,
        still = it.still?.toDto()
    )
}

@JvmName("listAudienceToDto")
internal fun List<Audience>.toDto() = map {
    AudienceLocalDto(
        count = it.count,
        country = it.country
    )
}

internal fun Watchability.toDto() = WatchabilityLocalDto(
    items = items.map {
        WatchabilityItemLocalDto(
            name = it.name,
            logo = it.logo?.toDto(),
            url = it.url
        )
    }
)

@JvmName("listPersonMovieToDto")
internal fun List<PersonMovie>.toDto() = map {
    PersonMovieLocalDto(
        id = it.id,
        name = it.name,
        enName = it.enName,
        photo = it.photo,
        description = it.description,
        profession = it.profession,
        enProfession = it.enProfession
    )
}
