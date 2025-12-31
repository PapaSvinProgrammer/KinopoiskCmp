package com.mordva.person.presentation.widget

import com.mordva.model.movie.ShortMovie
import com.mordva.ui.uiState.FactListUIState
import com.mordva.ui.uiState.MovieListUIState
import com.mordva.ui.uiState.PersonListUIState

internal data class UiState(
    val personState: PersonListUIState = PersonListUIState.Loading,
    val moviesState: MovieListUIState = MovieListUIState.Loading,
    val factState: FactListUIState = FactListUIState.Loading,
    val countAwards: Int? = null,
    val personSpouseState: PersonListUIState = PersonListUIState.Loading,
    val groups: Map<String, List<ShortMovie>> = mapOf(),
    val groupsKeys: List<String> = listOf(),
    val selectedGroup: Int = 0,
    val moviesFromGroup: List<ShortMovie> = listOf(),
)