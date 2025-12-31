package com.mordva.home.presentation.widget

import com.mordva.ui.uiState.CollectionListUIState
import com.mordva.ui.uiState.MovieListUIState

internal data class UIState(
    val movieDramaState: MovieListUIState = MovieListUIState.Loading,
    val movieBoevikState: MovieListUIState = MovieListUIState.Loading,
    val movieBest250State: MovieListUIState = MovieListUIState.Loading,
    val movieBest501State: MovieListUIState = MovieListUIState.Loading,
    val movieBest100State: MovieListUIState = MovieListUIState.Loading,
    val movieHBOState: MovieListUIState = MovieListUIState.Loading,
    val movieNetflixState: MovieListUIState = MovieListUIState.Loading,
    val collectionState: CollectionListUIState = CollectionListUIState.Loading,
)