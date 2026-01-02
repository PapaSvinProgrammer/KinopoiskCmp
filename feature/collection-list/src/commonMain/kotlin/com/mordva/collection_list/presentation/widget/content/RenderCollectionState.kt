package com.mordva.collection_list.presentation.widget.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.collection_list.presentation.widget.CollectionListUIState
import com.mordva.domain.model.image.CollectionMovie
import com.mordva.ui.widget.shimmer.ShimmerMovieDetailList

@Composable
internal fun RenderCollectionState(
    modifier: Modifier = Modifier,
    state: CollectionListUIState,
    onClick: (CollectionMovie) -> Unit,
    onLoadMore: () -> Unit
) {
    when (state) {
        CollectionListUIState.Loading -> ShimmerMovieDetailList(modifier)
        is CollectionListUIState.Success -> {
            SuccessCollectionContent(
                modifier = modifier,
                collectionMovies = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore
            )
        }
    }
}