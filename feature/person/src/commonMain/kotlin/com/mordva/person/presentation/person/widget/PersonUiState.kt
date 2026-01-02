package com.mordva.person.presentation.person.widget

import com.mordva.domain.model.movie.ShortMovie

internal data class PersonUiState(
    val personState: PersonListUIState = PersonListUIState.Loading,
//    val moviesState: MovieListUIState = MovieListUIState.Loading,
    val factState: FactListUIState = FactListUIState.Loading,
    val countAwards: Int? = null,
    val personSpouseState: PersonListUIState = PersonListUIState.Loading,
    val groups: Map<String, List<ShortMovie>> = mapOf(),
    val groupsKeys: List<String> = listOf(),
    val selectedGroup: Int = 0,
    val moviesFromGroup: List<ShortMovie> = listOf(),
)