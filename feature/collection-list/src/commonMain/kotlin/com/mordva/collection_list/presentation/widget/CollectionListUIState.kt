package com.mordva.collection_list.presentation.widget

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.image.CollectionMovie

@Immutable
sealed interface CollectionListUIState {
    data class Success(val data: List<CollectionMovie>) : CollectionListUIState
    data object Loading : CollectionListUIState
}