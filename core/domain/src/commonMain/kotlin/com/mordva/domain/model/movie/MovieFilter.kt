import com.mordva.domain.model.DateRange
import com.mordva.domain.model.Range
import com.mordva.domain.model.movie.MovieStatus
import com.mordva.domain.model.movie.MovieType
import com.mordva.domain.model.movie.RatingMpaa

@ConsistentCopyVisibility
data class MovieFilter private constructor(
    // Тип и статус
    val includeTypes: List<MovieType>? = null,
    val excludeTypes: List<MovieType>? = null,
    val includeTypeNumbers: List<Int>? = null,
    val excludeTypeNumbers: List<Int>? = null,
    val isSeries: Boolean? = null,  // null = any, true = series only, false = movies only
    val includeStatuses: List<MovieStatus>? = null,
    val excludeStatuses: List<MovieStatus>? = null,

    // Годы
    val year: Range<Int>? = null,
    val releaseYearsStart: Range<Int>? = null,
    val releaseYearsEnd: Range<Int>? = null,

    // Рейтинги
    val ratingKp: Range<Float>? = null,
    val ratingImdb: Range<Float>? = null,
    val ratingTmdb: Range<Float>? = null,
    val ratingFilmCritics: Range<Float>? = null,
    val ratingRussianFilmCritics: Range<Float>? = null,
    val ratingAwait: Range<Float>? = null,
    val includeRatingMpaa: List<RatingMpaa>? = null,
    val excludeRatingMpaa: List<RatingMpaa>? = null,

    // Возраст
    val ageRating: Range<Int>? = null,

    // Голоса / популярность
    val votesKp: Range<Int>? = null,
    val votesImdb: Range<Int>? = null,
    val votesTmdb: Range<Int>? = null,
    val votesFilmCritics: Range<Int>? = null,
    val votesRussianFilmCritics: Range<Int>? = null,
    val votesAwait: Range<Int>? = null,

    // Финансы
    val budgetValue: Range<Long>? = null,
    val feesWorldValue: Range<Long>? = null,
    val feesUsaValue: Range<Long>? = null,
    val feesRussiaValue: Range<Long>? = null,

    // Аудитория и длительность
    val audienceCount: Range<Int>? = null,
    val movieLength: Range<Int>? = null,
    val seriesLength: Range<Int>? = null,
    val totalSeriesLength: Range<Int>? = null,

    // Жанры, страны, сети
    val includeGenres: List<String>? = null,
    val excludeGenres: List<String>? = null,
    val includeCountries: List<String>? = null,
    val excludeCountries: List<String>? = null,
    val includeNetworks: List<String>? = null,
    val excludeNetworks: List<String>? = null,

    // Премьеры
    val premiereWorld: DateRange? = null,
    val premiereUsa: DateRange? = null,
    val premiereRussia: DateRange? = null,
    val premiereDigital: DateRange? = null,
    val premiereCinema: DateRange? = null,
    val premiereDvd: DateRange? = null,
    val premiereBluRay: DateRange? = null,
    val includePremiereCountries: List<String>? = null,
    val excludePremiereCountries: List<String>? = null,

    // Похожие, сиквелы
    val includeSimilarMoviesIds: List<Long>? = null,
    val excludeSimilarMoviesIds: List<Long>? = null,
    val includeSequelsAndPrequelsIds: List<Long>? = null,
    val excludeSequelsAndPrequelsIds: List<Long>? = null,

    // Платформы, списки, билеты
    val includeWatchabilityItems: List<String>? = null,
    val excludeWatchabilityItems: List<String>? = null,
    val ticketsOnSale: Boolean? = null,
    val includeLists: List<String>? = null,
    val excludeLists: List<String>? = null,
) {
    companion object {
        fun build(block: Builder.() -> Unit): MovieFilter = Builder().apply(block).build()
    }

    class Builder {

        // -------- Тип и статус --------

        var includeTypes: MutableList<MovieType>? = null
        var excludeTypes: MutableList<MovieType>? = null
        var includeTypeNumbers: MutableList<Int>? = null
        var excludeTypeNumbers: MutableList<Int>? = null
        var isSeries: Boolean? = null
        var includeStatuses: MutableList<MovieStatus>? = null
        var excludeStatuses: MutableList<MovieStatus>? = null

        fun includeType(vararg types: MovieType) {
            includeTypes = (includeTypes ?: mutableListOf()).apply { addAll(types) }
        }

        fun excludeType(vararg types: MovieType) {
            excludeTypes = (excludeTypes ?: mutableListOf()).apply { addAll(types) }
        }

        fun includeTypeNumber(vararg numbers: Int) {
            includeTypeNumbers =
                (includeTypeNumbers ?: mutableListOf()).apply { addAll(numbers.toList()) }
        }

        fun excludeTypeNumber(vararg numbers: Int) {
            excludeTypeNumbers =
                (excludeTypeNumbers ?: mutableListOf()).apply { addAll(numbers.toList()) }
        }

        fun seriesOnly() {
            isSeries = true
        }

        fun moviesOnly() {
            isSeries = false
        }

        fun includeStatus(vararg statuses: MovieStatus) {
            includeStatuses = (includeStatuses ?: mutableListOf()).apply { addAll(statuses) }
        }

        fun excludeStatus(vararg statuses: MovieStatus) {
            excludeStatuses = (excludeStatuses ?: mutableListOf()).apply { addAll(statuses) }
        }

        // -------- Годы --------

        var year: Range<Int>? = null
        var releaseYearsStart: Range<Int>? = null
        var releaseYearsEnd: Range<Int>? = null

        fun yearRange(min: Int? = null, max: Int? = null) {
            year = Range(min, max)
        }

        fun minYear(min: Int) {
            year = (year ?: Range()).copy(min = min)
        }

        fun maxYear(max: Int) {
            year = (year ?: Range()).copy(max = max)
        }

        fun releaseYearsStartRange(min: Int? = null, max: Int? = null) {
            releaseYearsStart = Range(min, max)
        }

        fun releaseYearsEndRange(min: Int? = null, max: Int? = null) {
            releaseYearsEnd = Range(min, max)
        }

        // -------- Рейтинги --------

        var ratingKp: Range<Float>? = null
        var ratingImdb: Range<Float>? = null
        var ratingTmdb: Range<Float>? = null
        var ratingFilmCritics: Range<Float>? = null
        var ratingRussianFilmCritics: Range<Float>? = null
        var ratingAwait: Range<Float>? = null
        var includeRatingMpaa: MutableList<RatingMpaa>? = null
        var excludeRatingMpaa: MutableList<RatingMpaa>? = null

        fun ratingKpRange(min: Float? = null, max: Float? = null) {
            ratingKp = Range(min, max)
        }

        fun ratingImdbRange(min: Float? = null, max: Float? = null) {
            ratingImdb = Range(min, max)
        }

        fun ratingTmdbRange(min: Float? = null, max: Float? = null) {
            ratingTmdb = Range(min, max)
        }

        fun ratingFilmCriticsRange(min: Float? = null, max: Float? = null) {
            ratingFilmCritics = Range(min, max)
        }

        fun ratingRussianFilmCriticsRange(min: Float? = null, max: Float? = null) {
            ratingRussianFilmCritics = Range(min, max)
        }

        fun ratingAwaitRange(min: Float? = null, max: Float? = null) {
            ratingAwait = Range(min, max)
        }

        fun includeMpaa(vararg mpaa: RatingMpaa) {
            includeRatingMpaa = (includeRatingMpaa ?: mutableListOf()).apply { addAll(mpaa) }
        }

        fun excludeMpaa(vararg mpaa: RatingMpaa) {
            excludeRatingMpaa = (excludeRatingMpaa ?: mutableListOf()).apply { addAll(mpaa) }
        }

        // -------- Возраст --------

        var ageRating: Range<Int>? = null

        fun ageRange(min: Int? = null, max: Int? = null) {
            ageRating = Range(min, max)
        }

        // -------- Голоса --------

        var votesKp: Range<Int>? = null
        var votesImdb: Range<Int>? = null
        var votesTmdb: Range<Int>? = null
        var votesFilmCritics: Range<Int>? = null
        var votesRussianFilmCritics: Range<Int>? = null
        var votesAwait: Range<Int>? = null

        fun votesKpRange(min: Int? = null, max: Int? = null) {
            votesKp = Range(min, max)
        }

        fun votesImdbRange(min: Int? = null, max: Int? = null) {
            votesImdb = Range(min, max)
        }

        fun votesTmdbRange(min: Int? = null, max: Int? = null) {
            votesTmdb = Range(min, max)
        }

        fun votesFilmCriticsRange(min: Int? = null, max: Int? = null) {
            votesFilmCritics = Range(min, max)
        }

        fun votesRussianFilmCriticsRange(min: Int? = null, max: Int? = null) {
            votesRussianFilmCritics = Range(min, max)
        }

        fun votesAwaitRange(min: Int? = null, max: Int? = null) {
            votesAwait = Range(min, max)
        }

        // -------- Финансы --------

        var budgetValue: Range<Long>? = null
        var feesWorldValue: Range<Long>? = null
        var feesUsaValue: Range<Long>? = null
        var feesRussiaValue: Range<Long>? = null

        fun budgetRange(min: Long? = null, max: Long? = null) {
            budgetValue = Range(min, max)
        }

        fun feesWorldRange(min: Long? = null, max: Long? = null) {
            feesWorldValue = Range(min, max)
        }

        fun feesUsaRange(min: Long? = null, max: Long? = null) {
            feesUsaValue = Range(min, max)
        }

        fun feesRussiaRange(min: Long? = null, max: Long? = null) {
            feesRussiaValue = Range(min, max)
        }

        // -------- Аудитория и длительность --------

        var audienceCount: Range<Int>? = null
        var movieLength: Range<Int>? = null
        var seriesLength: Range<Int>? = null
        var totalSeriesLength: Range<Int>? = null

        fun audienceRange(min: Int? = null, max: Int? = null) {
            audienceCount = Range(min, max)
        }

        fun movieLengthRange(min: Int? = null, max: Int? = null) {
            movieLength = Range(min, max)
        }

        fun seriesLengthRange(min: Int? = null, max: Int? = null) {
            seriesLength = Range(min, max)
        }

        fun totalSeriesLengthRange(min: Int? = null, max: Int? = null) {
            totalSeriesLength = Range(min, max)
        }

        // -------- Жанры / страны / сети --------

        var includeGenres: MutableList<String>? = null
        var excludeGenres: MutableList<String>? = null
        var includeCountries: MutableList<String>? = null
        var excludeCountries: MutableList<String>? = null
        var includeNetworks: MutableList<String>? = null
        var excludeNetworks: MutableList<String>? = null

        fun includeGenre(vararg genres: String) {
            includeGenres = (includeGenres ?: mutableListOf()).apply { addAll(genres) }
        }

        fun excludeGenre(vararg genres: String) {
            excludeGenres = (excludeGenres ?: mutableListOf()).apply { addAll(genres) }
        }

        fun includeCountry(vararg countries: String) {
            includeCountries = (includeCountries ?: mutableListOf()).apply { addAll(countries) }
        }

        fun excludeCountry(vararg countries: String) {
            excludeCountries = (excludeCountries ?: mutableListOf()).apply { addAll(countries) }
        }

        fun includeNetwork(vararg networks: String) {
            includeNetworks = (includeNetworks ?: mutableListOf()).apply { addAll(networks) }
        }

        fun excludeNetwork(vararg networks: String) {
            excludeNetworks = (excludeNetworks ?: mutableListOf()).apply { addAll(networks) }
        }

        // -------- Премьеры --------

        var premiereWorld: DateRange? = null
        var premiereUsa: DateRange? = null
        var premiereRussia: DateRange? = null
        var premiereDigital: DateRange? = null
        var premiereCinema: DateRange? = null
        var premiereDvd: DateRange? = null
        var premiereBluRay: DateRange? = null
        var includePremiereCountries: MutableList<String>? = null
        var excludePremiereCountries: MutableList<String>? = null

        fun premiereWorldRange(from: String? = null, to: String? = null) {
            premiereWorld = DateRange(from, to)
        }

        fun premiereUsaRange(from: String? = null, to: String? = null) {
            premiereUsa = DateRange(from, to)
        }

        fun premiereRussiaRange(from: String? = null, to: String? = null) {
            premiereRussia = DateRange(from, to)
        }

        fun premiereDigitalRange(from: String? = null, to: String? = null) {
            premiereDigital = DateRange(from, to)
        }

        fun premiereCinemaRange(from: String? = null, to: String? = null) {
            premiereCinema = DateRange(from, to)
        }

        fun premiereDvdRange(from: String? = null, to: String? = null) {
            premiereDvd = DateRange(from, to)
        }

        fun premiereBluRayRange(from: String? = null, to: String? = null) {
            premiereBluRay = DateRange(from, to)
        }

        fun includePremiereCountry(vararg countries: String) {
            includePremiereCountries =
                (includePremiereCountries ?: mutableListOf()).apply { addAll(countries) }
        }

        fun excludePremiereCountry(vararg countries: String) {
            excludePremiereCountries =
                (excludePremiereCountries ?: mutableListOf()).apply { addAll(countries) }
        }

        // -------- Платформы / списки / билеты --------

        var includeWatchabilityItems: MutableList<String>? = null
        var excludeWatchabilityItems: MutableList<String>? = null
        var ticketsOnSale: Boolean? = null
        var includeLists: MutableList<String>? = null
        var excludeLists: MutableList<String>? = null

        fun includeWatchability(vararg items: String) {
            includeWatchabilityItems =
                (includeWatchabilityItems ?: mutableListOf()).apply { addAll(items) }
        }

        fun excludeWatchability(vararg items: String) {
            excludeWatchabilityItems =
                (excludeWatchabilityItems ?: mutableListOf()).apply { addAll(items) }
        }

        fun ticketsOnSale(onSale: Boolean) {
            ticketsOnSale = onSale
        }

        fun includeList(vararg lists: String) {
            includeLists = (includeLists ?: mutableListOf()).apply { addAll(lists) }
        }

        fun excludeList(vararg lists: String) {
            excludeLists = (excludeLists ?: mutableListOf()).apply { addAll(lists) }
        }

        fun build(): MovieFilter = MovieFilter(
            includeTypes = includeTypes,
            excludeTypes = excludeTypes,
            includeTypeNumbers = includeTypeNumbers,
            excludeTypeNumbers = excludeTypeNumbers,
            isSeries = isSeries,
            includeStatuses = includeStatuses,
            excludeStatuses = excludeStatuses,
            year = year,
            releaseYearsStart = releaseYearsStart,
            releaseYearsEnd = releaseYearsEnd,
            ratingKp = ratingKp,
            ratingImdb = ratingImdb,
            ratingTmdb = ratingTmdb,
            ratingFilmCritics = ratingFilmCritics,
            ratingRussianFilmCritics = ratingRussianFilmCritics,
            ratingAwait = ratingAwait,
            includeRatingMpaa = includeRatingMpaa,
            excludeRatingMpaa = excludeRatingMpaa,
            ageRating = ageRating,
            votesKp = votesKp,
            votesImdb = votesImdb,
            votesTmdb = votesTmdb,
            votesFilmCritics = votesFilmCritics,
            votesRussianFilmCritics = votesRussianFilmCritics,
            votesAwait = votesAwait,
            budgetValue = budgetValue,
            feesWorldValue = feesWorldValue,
            feesUsaValue = feesUsaValue,
            feesRussiaValue = feesRussiaValue,
            audienceCount = audienceCount,
            movieLength = movieLength,
            seriesLength = seriesLength,
            totalSeriesLength = totalSeriesLength,
            includeGenres = includeGenres,
            excludeGenres = excludeGenres,
            includeCountries = includeCountries,
            excludeCountries = excludeCountries,
            includeNetworks = includeNetworks,
            excludeNetworks = excludeNetworks,
            premiereWorld = premiereWorld,
            premiereUsa = premiereUsa,
            premiereRussia = premiereRussia,
            premiereDigital = premiereDigital,
            premiereCinema = premiereCinema,
            premiereDvd = premiereDvd,
            premiereBluRay = premiereBluRay,
            includePremiereCountries = includePremiereCountries,
            excludePremiereCountries = excludePremiereCountries,
            includeWatchabilityItems = includeWatchabilityItems,
            excludeWatchabilityItems = excludeWatchabilityItems,
            ticketsOnSale = ticketsOnSale,
            includeLists = includeLists,
            excludeLists = excludeLists,
        )
    }
}