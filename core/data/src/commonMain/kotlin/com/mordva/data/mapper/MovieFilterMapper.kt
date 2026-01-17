package com.mordva.data.mapper

import com.mordva.domain.model.DateRange
import com.mordva.domain.model.movie.MovieFilter
import kotlin.to

fun MovieFilter.toQueryParams(): List<Pair<String, String>> {

    val params = mutableListOf<Pair<String, String>>()

    // -------- Тип и статус --------

    params.addAll(
        "type",
        mergeIncludeExclude(includeTypes, excludeTypes) { it.name.lowercase() }
    )

    params.addAll(
        "typeNumber",
        mergeIncludeExclude(includeTypeNumbers, excludeTypeNumbers) { it.toString() }
    )

    params.add("isSeries", isSeries?.toString())

    params.addAll(
        "status",
        mergeIncludeExclude(includeStatuses, excludeStatuses) { it.name.lowercase() }
    )

    // -------- Годы --------

    params.add("year", year?.toApiString())
    params.add("releaseYears.start", releaseYearsStart?.toApiString())
    params.add("releaseYears.end", releaseYearsEnd?.toApiString())

    // -------- Рейтинги --------

    params.add("rating.kp", ratingKp?.toApiString())
    params.add("rating.imdb", ratingImdb?.toApiString())
    params.add("rating.tmdb", ratingTmdb?.toApiString())
    params.add("rating.filmCritics", ratingFilmCritics?.toApiString())
    params.add("rating.russianFilmCritics", ratingRussianFilmCritics?.toApiString())
    params.add("rating.await", ratingAwait?.toApiString())

    params.addAll(
        "ratingMpaa",
        mergeIncludeExclude(includeRatingMpaa, excludeRatingMpaa) { it.name }
    )

    // -------- Возраст --------

    params.add("ageRating", ageRating?.toApiString())

    // -------- Голоса --------

    params.add("votes.kp", votesKp?.toApiString())
    params.add("votes.imdb", votesImdb?.toApiString())
    params.add("votes.tmdb", votesTmdb?.toApiString())
    params.add("votes.filmCritics", votesFilmCritics?.toApiString())
    params.add("votes.russianFilmCritics", votesRussianFilmCritics?.toApiString())
    params.add("votes.await", votesAwait?.toApiString())

    // -------- Финансы --------

    params.add("budget.value", budgetValue?.toApiString())
    params.add("fees.world.value", feesWorldValue?.toApiString())
    params.add("fees.usa.value", feesUsaValue?.toApiString())
    params.add("fees.russia.value", feesRussiaValue?.toApiString())

    // -------- Аудитория / длительность --------

    params.add("audience.count", audienceCount?.toApiString())
    params.add("movieLength", movieLength?.toApiString())
    params.add("seriesLength", seriesLength?.toApiString())
    params.add("totalSeriesLength", totalSeriesLength?.toApiString())

    // -------- Жанры / страны / сети --------

    params.addAll(
        "genres.name",
        mergeIncludeExclude(includeGenres, excludeGenres) { it }
    )

    params.addAll(
        "countries.name",
        mergeIncludeExclude(includeCountries, excludeCountries) { it }
    )

    params.addAll(
        "networks.items.name",
        mergeIncludeExclude(includeNetworks, excludeNetworks) { it }
    )

    // -------- Премьеры --------

    params.add("premiere.world", premiereWorld.toDateRangeString())
    params.add("premiere.usa", premiereUsa.toDateRangeString())
    params.add("premiere.russia", premiereRussia.toDateRangeString())
    params.add("premiere.digital", premiereDigital.toDateRangeString())
    params.add("premiere.cinema", premiereCinema.toDateRangeString())

    params.addAll(
        "premiere.country",
        mergeIncludeExclude(includePremiereCountries, excludePremiereCountries) { it }
    )

    // -------- Платформы / списки / билеты --------

    params.addAll(
        "watchability.items.name",
        mergeIncludeExclude(includeWatchabilityItems, excludeWatchabilityItems) { it }
    )

    params.add("ticketsOnSale", ticketsOnSale?.toString())

    params.addAll(
        "lists",
        mergeIncludeExclude(includeLists, excludeLists) { it }
    )

    return params
}


private fun MutableList<Pair<String, String>>.add(
    key: String,
    value: String?
) {
    if (!value.isNullOrBlank()) {
        add(key to value)
    }
}

private fun MutableList<Pair<String, String>>.addAll(
    key: String,
    values: List<String>?
) {
    values?.forEach { add(key to it) }
}

private fun <T> mergeIncludeExclude(
    include: List<T>?,
    exclude: List<T>?,
    mapper: (T) -> String
): List<String>? {
    val result = mutableListOf<String>()
    include?.forEach { result += mapper(it) }
    exclude?.forEach { result += "!${mapper(it)}" }
    return result.takeIf { it.isNotEmpty() }
}

private fun DateRange?.toDateRangeString(): String? =
    when {
        this == null -> null
        from != null && to != null -> "$from-$to"
        from != null -> from
        to != null -> to
        else -> null
    }
