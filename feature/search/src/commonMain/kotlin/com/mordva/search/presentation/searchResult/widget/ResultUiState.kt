package com.mordva.search.presentation.searchResult.widget

import com.mordva.ui.uiState.MovieListUIState

internal data class ResultUiState(
    val page: Int = 1,
    val movieState: MovieListUIState = MovieListUIState.Loading
)