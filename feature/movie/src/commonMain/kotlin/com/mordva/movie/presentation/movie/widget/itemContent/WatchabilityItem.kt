package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.mordva.model.category.WatchabilityItem
import com.mordva.movie.presentation.movie.widget.component.WatchabilityDescription

internal fun LazyListScope.watchabilityItem(
    items: List<WatchabilityItem>,
    onWatchabilityClick: () -> Unit,
) {
    if (items.isEmpty()) return

    item {
        WatchabilityDescription(
            modifier = Modifier.clickable(onClick = onWatchabilityClick),
            count = items.size
        )
    }
}
