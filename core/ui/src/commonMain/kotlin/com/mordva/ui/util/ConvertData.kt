package com.mordva.ui.util

import com.mordva.util.Constants
import kotlin.math.round
import kotlin.math.roundToInt

object ConvertData {
    fun convertRatingRange(position: ClosedFloatingPointRange<Float>): String {
        val start = position.start.roundToInt()
        val end = position.endInclusive.roundToInt()

        val startStr = "от $start"
        val endStr = "до $end"

        if (start == end) {
            return "только $start"
        }

        if (start == 0 && end == 10) {
            return "неважно"
        }

        if (start == 0) {
            return endStr
        }

        if (end == 10) {
            return startStr
        }

        return startStr + endStr
    }

    fun convertYearRange(start: Int, end: Int): String {
        if (start == end) {
            return start.toString()
        }

        val currentYear = FormatDate.getCurrentYear()

        if (start == Constants.LAST_YEAR && end == currentYear) {
            return "Любой"
        }

        if (start == Constants.LAST_YEAR) {
            return "по $end"
        }

        if (end == currentYear) {
            return "с $start"
        }

        return "с $start по $end"
    }

    fun getAlternativeNameForMovie(
        alternativeName: String?,
        year: Int?,
        genres: List<String>,
        start: Int?,
        end: Int?
    ): String {
        alternativeName?.let { return it }
        val year = convertDateCreated(year, start, end)

        if (year.isNotEmpty()) {
            return year
        }

        if (genres.isNotEmpty()) {
            return genres.joinToString(", ")
        }

        return ""
    }

    fun convertDateCreated(year: Int?, start: Int?, end: Int?): String {
        if (start == end) {
            return start.toString()
        }

        start?.let {
            if (end == null) {
                return "$start-..."
            }

            return "$start-$end"
        }

        year?.let {
            return it.toString()
        }

        return ""
    }

    fun convertQueryParameters(
        category: String,
        sortBy: String,
        genres: List<String>,
        counties: List<String>,
        year: Pair<Int, Int>,
        rating: ClosedFloatingPointRange<Float>
    ): List<Pair<String, String>> {
        val res = mutableListOf<Pair<String, String>>()

        when (category) {
            "Фильмы" -> res.add(Constants.IS_SERIES_FIELD to Constants.FALSE)
            "Сериалы" -> res.add(Constants.IS_SERIES_FIELD to Constants.TRUE)
        }

        when (sortBy) {
            "Рейтингу" -> res.add(Constants.SORT_FIELD to Constants.RATING_KP_FIELD)
            "Популярности" -> res.add(Constants.SORT_FIELD to Constants.VOTES_KP_FIELD)
            "Дате" -> res.add(Constants.SORT_FIELD to Constants.YEAR_FIELD)
        }

        res.add(Constants.SORT_TYPE to Constants.SORT_DESC)

        genres.forEach { res.add(Constants.GENRES_NAME_FIELD to it) }
        counties.forEach { res.add(Constants.COUNTRIES_NAME_FIELD to it) }

        val startRating = rating.start.roundToInt()
        val endRating = rating.endInclusive.roundToInt()

        res.add(Constants.RATING_KP_FIELD to "$startRating-$endRating")
        res.add(Constants.YEAR_FIELD to "${year.first}-${year.second}")

        return res
    }

    fun convertRatingKP(rating: Float): String {
        val temp = (round(rating * 10) / 10)
        return temp.toString()
    }
}