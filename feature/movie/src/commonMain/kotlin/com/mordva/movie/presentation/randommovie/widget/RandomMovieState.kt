package com.mordva.movie.presentation.randommovie.widget

import com.mordva.movie.presentation.randommovie.widget.component.RandomMoviePagerItemType

internal data class RandomMovieState(
    val items: List<RandomMovieItemState> = listOf(),
    val pagerItemState: RandomMoviePagerItemType = RandomMoviePagerItemType.PAGER_ITEM,
    val expandedToolbar: Boolean = true,
)

internal fun RandomMovieState.getMoviePosters() = items.map {
    (it as? RandomMovieItemState.Success)?.movie?.poster?.url.toString()
}