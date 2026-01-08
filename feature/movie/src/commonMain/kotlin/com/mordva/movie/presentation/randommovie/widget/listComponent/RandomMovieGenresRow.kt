package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.Modifier
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.util.customOffset
import com.mordva.ui.widget.component.DsChip

internal fun LazyListScope.randomMovieGenresRow(genres: List<String>, offsetY: Float) {
    item {
        Row(
            horizontalArrangement = Arrangement.spacedBy(DsSpacer.M10),
            modifier = Modifier
                .padding(horizontal = DsSpacer.M10)
                .customOffset(yOffset = offsetY)
                .horizontalScroll(rememberScrollState())
        ) {
            genres.forEach { DsChip(it) }
        }
    }
}