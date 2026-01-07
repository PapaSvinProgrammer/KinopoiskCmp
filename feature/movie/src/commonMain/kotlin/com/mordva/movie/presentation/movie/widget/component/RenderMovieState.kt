package com.mordva.movie.presentation.movie.widget.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Movie
import com.mordva.movie.presentation.movie_list.widget.MovieListState
import com.mordva.ui.widget.lazyComponent.EndlessLazyColumn
import com.mordva.movie.presentation.movie.widget.listItem.MovieDetailCard
import com.mordva.ui.widget.shimmer.ShimmerMovieDetailList

@Composable
internal fun RenderMovieState(
    modifier: Modifier,
    state: MovieListState,
    onClick: (Movie) -> Unit,
    loadMore: () -> Unit
) {
    when (state) {
        is MovieListState.Success -> {
            MainPersonContent(
                modifier = modifier,
                movies = state.data,
                onClick = onClick,
                loadMore = loadMore
            )
        }
        else -> ShimmerMovieDetailList(modifier)
    }
}

@Composable
private fun MainPersonContent(
    modifier: Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
    loadMore: () -> Unit
) {
    EndlessLazyColumn(
        modifier = modifier,
        items = movies,
        loadMore = loadMore
    ) { _, it ->
        MovieDetailCard(it) { onClick(it) }
        HorizontalDivider(modifier = Modifier.padding(start = 110.dp))
    }
}