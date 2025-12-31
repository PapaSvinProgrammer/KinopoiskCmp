package com.mordva.movie.presentation.groupPerson

import androidx.lifecycle.ViewModel
import com.mordva.movie.domain.GetPersonLittleById
import com.mordva.movie.domain.GroupPersonsByProfession
import com.mordva.movie.domain.UnionPersonsAndPersonMovie
import com.mordva.movie.domain.model.UnionParams
import com.mordva.movie.presentation.groupPerson.widget.GroupUiState
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import com.mordva.util.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class GroupPersonViewModel(
    persons: List<PersonMovieScreenObject>,
    private val getPersonByFilter: GetPersonLittleById,
    private val groupPersonsByProfession: GroupPersonsByProfession,
    private val unionPersonsAndPersonMovie: UnionPersonsAndPersonMovie
) : ViewModel() {
    private val _uiState = MutableStateFlow(GroupUiState.Loading as GroupUiState)
    val uiState = _uiState.asStateFlow()

    init {
        getGroupedPersons(persons)
    }

    private fun getGroupedPersons(persons: List<PersonMovieScreenObject>) = launchWithoutOld(GET_PERSONS) {
        val responsePersons = multiRequest(persons) { person ->
            getPersonByFilter.execute(person.id)
        }

        val unionParams = UnionParams(
            personsList = responsePersons,
            personsScreenObjectList = persons
        )

        val mergedList = unionPersonsAndPersonMovie.execute(unionParams)
        val groupedPersons = groupPersonsByProfession.execute(mergedList)

        _uiState.value = GroupUiState.Success(groupedPersons)
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_PERSONS = "get_persons"
    }
}
