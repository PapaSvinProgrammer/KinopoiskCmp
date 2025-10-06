package com.mordva.movie.presentation.groupPerson.widget

import com.mordva.movie.domain.model.PersonMovieExtended

internal sealed interface GroupUiState {
    data class Success(val data: Map<String, List<PersonMovieExtended>>) : GroupUiState
    data object Loading : GroupUiState
}

