package com.mordva.movie.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.util.ConvertData
import com.mordva.ui.util.PrettyData

@Composable
internal fun ExpandedContent(
    movie: Movie,
    isCustomRating: Int? = null,
    isWillWatchPackage: Boolean = false,
    onEvaluate: () -> Unit,
    onAddIntoFuturePackage: () -> Unit,
    onShare: () -> Unit,
    onMore: () -> Unit
) {
    val date = ConvertData.convertDateCreated(
        year = movie.year,
        start = movie.releaseYears.firstOrNull()?.start,
        end = movie.releaseYears.firstOrNull()?.end
    )
    val genres = movie.genres.take(2).joinToString(", ") { it.name }
    val countries = movie.countries.take(2).joinToString(", ") { it.name }
    var length = PrettyData.getPrettyLength(movie.movieLength ?: 0)
    val age = PrettyData.getPrettyAgeRating(movie.ageRating ?: 0)

    if (movie.isSeries == true) {
        val seasonCount = movie.seasonsInfo?.count { it.number != 0 } ?: 0
        length = PrettyData.getPrettyCountSeasons(seasonCount)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (movie.backdrop?.url.isCorrectUrl() && movie.logo?.url.isCorrectUrl()) {
            ContentWithBackdrop(
                movie = movie,
                genres = genres,
                date = date,
                countries = countries,
                length = length,
                age = age,
                customRating = isCustomRating,
            )
        }
        else {
            ContentWithoutBackdrop(
                movie = movie,
                genres = genres,
                date = date,
                countries = countries,
                customRating = isCustomRating,
                length = length,
                age = age
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ExpandedNavigationBar(
            customRating = isCustomRating,
            isWillWatchPackage = isWillWatchPackage,
            onEvaluate = onEvaluate,
            onShare = onShare,
            onMore = onMore,
            onAddIntoFuturePackage = onAddIntoFuturePackage
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
    }
}