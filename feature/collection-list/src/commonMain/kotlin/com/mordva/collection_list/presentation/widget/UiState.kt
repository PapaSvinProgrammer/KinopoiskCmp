package com.mordva.collection_list.presentation.widget

internal data class UiState(
    val page: Int = 1,
    val collectionState: CollectionListUIState = CollectionListUIState.Loading
)