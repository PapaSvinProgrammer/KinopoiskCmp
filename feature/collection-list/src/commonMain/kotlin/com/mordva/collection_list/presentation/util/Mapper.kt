package com.mordva.collection_list.presentation.util

import com.mordva.collection_list.presentation.widget.UiState
import com.mordva.model.image.CollectionMovie
import com.mordva.ui.uiState.CollectionUIState

internal fun UiState.toMutableCollectionList(): MutableList<CollectionMovie> {
    return (collectionState as? CollectionUIState.Success)?.data?.toMutableList() ?: mutableListOf()
}