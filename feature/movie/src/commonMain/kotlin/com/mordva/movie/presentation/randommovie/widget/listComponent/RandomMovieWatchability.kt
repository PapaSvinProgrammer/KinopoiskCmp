package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.domain.model.category.WatchabilityItem
import com.mordva.movie.presentation.movie.widget.component.WatchabilityDescription

@Composable
internal fun RandomMovieWatchability(
    items: List<WatchabilityItem>,
    onWatchabilityClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) return

    WatchabilityDescription(
        modifier = modifier.clickable(onClick = onWatchabilityClick),
        count = items.size,
        images = items.map { it.logo }
    )
}