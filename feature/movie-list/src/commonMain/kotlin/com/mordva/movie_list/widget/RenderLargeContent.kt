package com.mordva.movie_list.widget

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.Movie
import com.mordva.ui.widget.component.BasicLoadingBox
import com.mordva.ui.widget.lazyComponent.EndlessLazyVerticalGrid
import com.mordva.ui.widget.listItems.MovieFillCard
import com.mordva.util.ListUIState

@Composable
internal fun RenderLargeContent(
    state: ListUIState<Movie>,
    modifier: Modifier,
    onLoadMore: () -> Unit,
    onClick: (Movie) -> Unit
) {
    when (state) {
        is ListUIState.Error<*> -> {}
        is ListUIState.Loading<*> -> BasicLoadingBox()
        is ListUIState.Success<Movie> -> {
            MainMovieContent(
                list = state.data,
                modifier = modifier,
                onLoadMore = onLoadMore,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MainMovieContent(
    list: List<Movie>,
    modifier: Modifier,
    onLoadMore: () -> Unit,
    onClick: (Movie) -> Unit
) {
    EndlessLazyVerticalGrid(
        modifier = modifier,
        list = list,
        onLoadMore = onLoadMore
    ) {
        MovieFillCard(
            name = it.name ?: "",
            image = it.poster?.url ?: "",
            rating = it.rating?.kp,
            top250 = it.top250,
            modifier = Modifier.height(300.dp),
            onClick = { onClick(it) }
        )
    }
}
