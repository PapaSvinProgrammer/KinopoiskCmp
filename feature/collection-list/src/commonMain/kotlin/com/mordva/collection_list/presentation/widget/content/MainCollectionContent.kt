package com.mordva.collection_list.presentation.widget.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mordva.navigation.MovieListGraph
import com.mordva.collection_list.presentation.widget.CollectionListUIState
import com.mordva.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCollectionContent(
    modifier: Modifier = Modifier,
    collectionState: CollectionListUIState,
    navController: NavController,
    onLoadMore: () -> Unit = {}
) {
    RenderCollectionState(
        modifier = modifier.fillMaxSize(),
        state = collectionState,
        onClick = {
            val query = arrayListOf(
                Constants.LISTS_FIELD to it.slug.toString(),
                Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                Constants.SORT_TYPE to Constants.SORT_DESC
            )

            navController.navigate(
                MovieListGraph.MovieListRoute(
                    title = it.name ?: "",
                    queryParameters = query
                )
            ) { launchSingleTop = true }
        },
        onLoadMore = onLoadMore
    )
}