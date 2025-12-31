package com.mordva.search.presentation.search_screen.state

import com.mordva.model.SearchItem
import com.mordva.model.image.CollectionMovie
import com.mordva.model.movie.Movie

internal sealed interface SearchScreenEvent {
    data object ShowSettings : SearchScreenEvent
    data class ShowItemContent(val item: SearchItem) : SearchScreenEvent
    data object ShowCollectionAll : SearchScreenEvent
    data class ShowMovieList(val collection: CollectionMovie) : SearchScreenEvent
    data class ShowCollectionList(val collection: String) : SearchScreenEvent
    data class ShowMovie(val movie: Movie) : SearchScreenEvent
    data class ShowAllMovies(val title: String) : SearchScreenEvent
}