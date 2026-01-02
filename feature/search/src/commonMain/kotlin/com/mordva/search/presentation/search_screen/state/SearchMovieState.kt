package com.mordva.search.presentation.search_screen.state

import com.mordva.domain.model.movie.Movie

internal sealed interface SearchMovieState {
    data object Init : SearchMovieState
    data class Success(val list: List<Movie>) : SearchMovieState
    data object Error : SearchMovieState
}

internal fun SearchMovieState.body() = (this as? SearchMovieState.Success)?.list ?: listOf()