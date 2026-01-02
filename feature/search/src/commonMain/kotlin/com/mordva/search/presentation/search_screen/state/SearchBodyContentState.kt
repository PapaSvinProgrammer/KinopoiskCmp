package com.mordva.search.presentation.search_screen.state

import com.mordva.domain.model.image.CollectionMovie
import com.mordva.domain.model.movie.Movie

internal sealed interface SearchBodyContentState {
    data class Success(
        val collections: List<CollectionMovie>,
        val topSerials: List<Movie>
    ) : SearchBodyContentState

    data object Loading : SearchBodyContentState
    data object Error : SearchBodyContentState
}
