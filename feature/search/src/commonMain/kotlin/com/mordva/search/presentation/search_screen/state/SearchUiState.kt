package com.mordva.search.presentation.search_screen.state

internal data class SearchUiState(
    val query: String = "",
    val isExpanded: Boolean = false,
//    val collectionsState: CollectionListUIState = CollectionListUIState.Loading,
//    val personState: PersonListUIState = PersonListUIState.Loading,
//    val topSerialsState: MovieListUIState = MovieListUIState.Loading,
    val selectedSearchIndex: Int = 0,
    val searchState: SearchListUIState = SearchListUIState.Error,
    val page: Int = 1
)