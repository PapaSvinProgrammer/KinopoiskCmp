package com.mordva.search.presentation.searchScreen.widget

import com.mordva.ui.uiState.CollectionListUIState
import com.mordva.ui.uiState.MovieListUIState
import com.mordva.ui.uiState.PersonListUIState
import com.mordva.ui.uiState.SearchListUIState

internal data class UiState(
    val query: String = "",
    val isExpanded: Boolean = false,
    val collectionsState: CollectionListUIState = CollectionListUIState.Loading,
    val personState: PersonListUIState = PersonListUIState.Loading,
    val topSerialsState: MovieListUIState = MovieListUIState.Loading,
    val selectedSearchIndex: Int = 0,
    val searchState: SearchListUIState = SearchListUIState.Error,
    val page: Int = 1
)