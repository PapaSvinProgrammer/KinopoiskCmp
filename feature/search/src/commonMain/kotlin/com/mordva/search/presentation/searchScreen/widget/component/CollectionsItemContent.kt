package com.mordva.search.presentation.searchScreen.widget.component

import androidx.compose.foundation.lazy.LazyListScope
import androidx.navigation.NavController
import com.mordva.navigation.CollectionListGraph
import com.mordva.search.presentation.searchScreen.util.navigateToMovieList
import com.mordva.ui.theme.Resources
import com.mordva.ui.uiState.CollectionListUIState
import com.mordva.ui.widget.renderState.RenderCollectionStateRow
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.collectionsItemContent(
    state: CollectionListUIState,
    navController: NavController,
    get: () -> Unit
) {
    item {
        get()

        RenderCollectionStateRow(
            state = state,
            title = stringResource(Resources.Strings.AdviseWatch),
            onClick = { navigateToMovieList(navController, it) },
            onShowAll = {
                navController.navigate(
                    CollectionListGraph.CollectionListRoute("Фильмы")
                )
            }
        )
    }
}