package com.mordva.search.presentation.search_screen.state

internal data class SearchUiState(
    val query: String = "",
    val isExpanded: Boolean = false,
    val selectedSearchIndex: Int = 1,
    val searchState: SearchListUIState = SearchListUIState.Error,
    val bodyContentState: SearchBodyContentState = SearchBodyContentState.Loading,
)