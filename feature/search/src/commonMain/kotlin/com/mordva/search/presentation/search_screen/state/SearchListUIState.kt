package com.mordva.search.presentation.search_screen.state

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.SearchItem

@Immutable
interface SearchListUIState {
    data class Success(val data: List<SearchItem>) : SearchListUIState
    data object Loading : SearchListUIState
    data object Error : SearchListUIState
}