package com.mordva.movie.presentation.randommovie

import androidx.lifecycle.viewModelScope
import com.mordva.movie.domain.movie.RandomMovieLoadItemsUseCase
import com.mordva.movie.presentation.randommovie.widget.RandomMovieEvent
import com.mordva.movie.presentation.randommovie.widget.RandomMovieItemState
import com.mordva.movie.presentation.randommovie.widget.RandomMovieState
import com.mordva.movie.presentation.randommovie.widget.component.RandomMoviePagerItemType
import com.mordva.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

internal class RandomMovieViewModel(
    private val loadItems: RandomMovieLoadItemsUseCase
) : BaseViewModel<RandomMovieEvent>() {
    private val pagerItemState = MutableStateFlow(RandomMoviePagerItemType.PAGER_ITEM)
    private val movieListState = MutableStateFlow<List<RandomMovieItemState>>(listOf())
    private val expandedToolbarState = MutableStateFlow(true)

    val state = combine(
        expandedToolbarState,
        movieListState,
        pagerItemState
    ) { expandedToolbar, movieList, pagerItem ->
        RandomMovieState(
            items = movieList,
            pagerItemState = pagerItem,
            expandedToolbar = expandedToolbar,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RandomMovieState()
    )

    init {
        loadMovies(2)
    }

    fun onPagerItemClicked() {
        pagerItemState.value = when (pagerItemState.value) {
            RandomMoviePagerItemType.PAGER_ITEM -> RandomMoviePagerItemType.BOTTOM_SHEET_ITEM
            RandomMoviePagerItemType.BOTTOM_SHEET_ITEM -> RandomMoviePagerItemType.PAGER_ITEM
        }
    }

    fun onSearchClamped() {
        expandedToolbarState.value = !expandedToolbarState.value
    }

    private fun loadMovies(count: Int) = launchWithoutOld(LOAD_MOVIES) {
        movieListState.value = List(count) { RandomMovieItemState.Loading }
        movieListState.value = loadItems.execute(count)
    }

    private companion object {
        const val LOAD_MOVIES = "load_movies_job"
    }
}
