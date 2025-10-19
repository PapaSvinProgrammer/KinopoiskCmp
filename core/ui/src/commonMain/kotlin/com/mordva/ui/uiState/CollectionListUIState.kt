package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.image.CollectionMovie

@Immutable
sealed interface CollectionListUIState {
    data class Success(val data: List<CollectionMovie>) : CollectionListUIState
    data object Loading : CollectionListUIState
}