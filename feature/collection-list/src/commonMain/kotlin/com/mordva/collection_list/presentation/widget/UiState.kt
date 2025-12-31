package com.mordva.collection_list.presentation.widget

import com.mordva.ui.uiState.CollectionListUIState

internal data class UiState(
    val page: Int = 1,
    val collectionState: CollectionListUIState = CollectionListUIState.Loading
)