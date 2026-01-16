package com.mordva.network.external.model.movie

data class MovieFilterDto(
    val notNullFields: MutableList<String>? = null,

    // Тип и статус
    val type: List<String>? = null,              // "movie", "tv-series", "cartoon", "!anime"
    val typeNumber: List<String>? = null,        // "1", "2", "!3"
    val isSeries: Boolean? = null,
    val status: List<String>? = null,            // "announced", "!filming"

    // Годы
    val year: List<String>? = null,              // "2020", "2018-2022", "!2023"
    val releaseYearsStart: List<String>? = null,
    val releaseYearsEnd: List<String>? = null,

    // Рейтинги
    val ratingKp: String? = null,                // "7-9.5", "8"
    val ratingImdb: String? = null,
    val ratingTmdb: String? = null,
    val ratingFilmCritics: String? = null,
    val ratingRussianFilmCritics: String? = null,
    val ratingAwait: String? = null,
    val ratingMpaa: List<String>? = null,        // "PG-13", "!R"

    // Возраст
    val ageRating: List<String>? = null,         // "16", "12-18", "!18"

    // Голоса / популярность
    val votesKp: String? = null,
    val votesImdb: String? = null,
    val votesTmdb: String? = null,
    val votesFilmCritics: String? = null,
    val votesRussianFilmCritics: String? = null,
    val votesAwait: String? = null,

    // Финансы
    val budgetValue: String? = null,
    val feesWorldValue: String? = null,
    val feesUsaValue: String? = null,
    val feesRussiaValue: String? = null,

    // Аудитория и длительность
    val audienceCount: String? = null,
    val movieLength: String? = null,
    val seriesLength: String? = null,
    val totalSeriesLength: String? = null,

    // Жанры, страны, сети
    val genresName: List<String>? = null,        // "драма", "!мелодрама", "+ужасы"
    val countriesName: List<String>? = null,     // "Россия", "+США", "!Франция"
    val networksItemsName: List<String>? = null,

    // Персоны
    val personsId: List<String>? = null,
    val personsProfession: List<String>? = null, // "актер", "!режиссер"
    val personsEnProfession: List<String>? = null,

    // Премьеры
    val premiereWorld: List<String>? = null,     // "2023-01-01", "2022-01-01-2023-12-31"
    val premiereUsa: List<String>? = null,
    val premiereRussia: List<String>? = null,
    val premiereDigital: List<String>? = null,
    val premiereCinema: List<String>? = null,
    val premiereCountry: List<String>? = null,

    // Платформы, списки, билеты
    val watchabilityItemsName: List<String>? = null, // "ivi", "!okko"
    val ticketsOnSale: Boolean? = null,
    val lists: List<String>? = null,             // "top250", "!top100"

    val updatedAt: List<String>? = null,
    val createdAt: List<String>? = null
)