package com.mordva.search.presentation.search_screen.state

import com.mordva.domain.model.local.History

internal data class SearchUiState(
    val query: String = "",
    val isExpanded: Boolean = false,
    val selectedSearchIndex: Int = 1,
    val searchHistory: List<History> = listOf(),
    val searchState: SearchListUIState = SearchListUIState.Error,
    val bodyContentState: SearchBodyContentState = SearchBodyContentState.Loading,
)