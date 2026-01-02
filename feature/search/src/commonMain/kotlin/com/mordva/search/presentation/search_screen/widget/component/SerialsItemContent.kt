package com.mordva.search.presentation.search_screen.widget.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.serialsItemContent(
//    state: MovieListState,
    onMovieClick: (Movie) -> Unit,
    onShowAll: (String) -> Unit,
) {
    item {
        val title = stringResource(Resources.Strings.PopularSerials)

//        RenderMovieStateRow(
//            state = state,
//            title = title,
//            onClick = onMovieClick,
//            onShowAll = { onShowAll(title) }
//        )
        Spacer(modifier = Modifier.height(130.dp))
    }
}