package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.mordva.model.movie.Movie
import com.mordva.movie.presentation.movie.widget.component.WatchabilityDescription

internal fun LazyListScope.watchabilityItem(
    movie: Movie,
    onWatchabilityClick: () -> Unit,
) {
    if (movie.watchability.items.isEmpty()) return

    item {
        WatchabilityDescription(
            modifier = Modifier.clickable(onClick = onWatchabilityClick),
            count = movie.watchability.items.size
        )
    }
}
