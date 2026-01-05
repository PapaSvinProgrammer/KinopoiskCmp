package com.mordva.movie.presentation.groupPerson

import com.mordva.movie.domain.GetPersonOptimizedById
import com.mordva.movie.domain.groupPersonsByProfession
import com.mordva.movie.domain.mergeWith
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.presentation.groupPerson.widget.GroupUiState
import com.mordva.util.BaseViewModel
import com.mordva.util.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class GroupPersonViewModel(
    persons: List<PersonMovieScreenObject>,
    private val getPersonByFilter: GetPersonOptimizedById,
) : BaseViewModel<Unit>() {
    private val _uiState = MutableStateFlow(GroupUiState.Loading as GroupUiState)
    val uiState = _uiState.asStateFlow()

    init {
        getGroupedPersons(persons)
    }

    private fun getGroupedPersons(
        persons: List<PersonMovieScreenObject>
    ) = launchWithoutOld(GET_PERSONS) {
        val responsePersons = multiRequest(persons) { person ->
            getPersonByFilter.execute(person.id)
        }

        val mergedList = responsePersons.mergeWith(persons)
        val groupedPersons = mergedList.groupPersonsByProfession()

        _uiState.value = GroupUiState.Success(groupedPersons)
    }

    private companion object {
        const val GET_PERSONS = "get_persons"
    }
}
