package com.mordva.search.presentation.search_screen.state

import com.mordva.domain.model.image.CollectionMovie

internal sealed interface SearchCollectionState {
    data object Init : SearchCollectionState
    data class Success(val list: List<CollectionMovie>) : SearchCollectionState
    data object Error : SearchCollectionState
}

internal fun SearchCollectionState.body(): List<CollectionMovie> {
    return (this as? SearchCollectionState.Success)?.list ?: listOf()
}