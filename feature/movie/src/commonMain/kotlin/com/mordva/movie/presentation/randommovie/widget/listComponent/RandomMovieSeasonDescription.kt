package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.domain.model.movie.Movie
import com.mordva.movie.presentation.movie.widget.component.SeasonDescription

@Composable
internal fun RandomMovieSeasonDescription(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    if (movie.isSeries == false) return

    movie.seasonsInfo?.let { seasonsInfo ->
        SeasonDescription(
            modifier = modifier.clickable { },
            countSeasons = seasonsInfo.filter { it.number != 0 }.size,
            countSeries = seasonsInfo
                .filter { it.number != 0 }
                .sumOf { it.episodesCount ?: 0 }
        )
    }
}