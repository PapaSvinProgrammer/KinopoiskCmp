package com.mordva.collection_list.presentation.util

import com.mordva.collection_list.presentation.widget.UiState
import com.mordva.collection_list.presentation.widget.CollectionListUIState
import com.mordva.domain.model.image.CollectionMovie

internal fun UiState.toMutableCollectionList(): MutableList<CollectionMovie> {
    return (collectionState as? CollectionListUIState.Success)?.data?.toMutableList() ?: mutableListOf()
}