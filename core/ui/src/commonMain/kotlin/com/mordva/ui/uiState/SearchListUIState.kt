package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.SearchItem

@Immutable
interface SearchListUIState {
    data class Success(val data: List<SearchItem>) : SearchListUIState
    data object Loading : SearchListUIState
    data object Error : SearchListUIState
}