package com.mordva.search.presentation.searchScreen.widget.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.MovieListGraph
import com.mordva.ui.theme.Resources
import com.mordva.ui.uiState.MovieListUIState
import com.mordva.ui.widget.renderState.RenderMovieStateRow
import com.mordva.util.Constants
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.serialsItemContent(
    state: MovieListUIState,
    navController: NavController,
    get: () -> Unit
) {
    item {
        val title = stringResource(Resources.Strings.PopularSerials)
        get()

        RenderMovieStateRow(
            state = state,
            title = title,
            onClick = {
                navController.navigate(MovieGraph.MovieRoute(it.id))
            },
            onShowAll = {
                val queryParams = listOf(
                    Constants.IS_SERIES_FIELD to Constants.TRUE,
                    Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                    Constants.SORT_TYPE to Constants.SORT_DESC
                )

                navController.navigate(
                    MovieListGraph.MovieListRoute(
                        title = title,
                        queryParameters = queryParams
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(130.dp))
    }
}