package com.mordva.search.presentation.search_screen.state

internal data class SearchCollectionMovieContentState(
    val movieState: SearchMovieState,
    val collectionState: SearchCollectionState,
    val searchState: SearchListUIState,
)

internal fun SearchCollectionMovieContentState.extractInit(): Boolean {
    return movieState is SearchMovieState.Init || collectionState is SearchCollectionState.Init
}

internal fun SearchCollectionMovieContentState.extractError(): Boolean {
    return movieState is SearchMovieState.Error || collectionState is SearchCollectionState.Error
}
