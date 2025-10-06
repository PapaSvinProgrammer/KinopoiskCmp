package com.mordva.collection_list.presentation.widget

import com.mordva.ui.uiState.CollectionUIState

internal data class UiState(
    val page: Int = 1,
    val collectionState: CollectionUIState = CollectionUIState.Loading
)