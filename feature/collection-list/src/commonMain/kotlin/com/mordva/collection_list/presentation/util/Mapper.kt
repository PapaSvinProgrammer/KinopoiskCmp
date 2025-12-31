package com.mordva.collection_list.presentation.util

import com.mordva.collection_list.presentation.widget.UiState
import com.mordva.model.image.CollectionMovie
import com.mordva.ui.uiState.CollectionListUIState

internal fun UiState.toMutableCollectionList(): MutableList<CollectionMovie> {
    return (collectionState as? CollectionListUIState.Success)?.data?.toMutableList() ?: mutableListOf()
}