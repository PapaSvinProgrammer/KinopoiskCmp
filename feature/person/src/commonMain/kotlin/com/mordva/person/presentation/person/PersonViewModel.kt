package com.mordva.person.presentation.person

import com.mordva.domain.model.movie.ShortMovie
import com.mordva.domain.repository.PersonRepository
import com.mordva.person.domain.GetPersonAwardsByDate
import com.mordva.person.domain.GroupShortMovie
import com.mordva.person.domain.model.AwardParams
import com.mordva.person.presentation.person.widget.FactListUIState
import com.mordva.person.presentation.person.widget.PersonListUIState
import com.mordva.person.presentation.person.widget.PersonUiState
import com.mordva.util.BaseViewModel
import com.mordva.util.cancelAllJobs
import com.mordva.util.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class PersonViewModel(
    private val getPersonAwardsByDate: GetPersonAwardsByDate,
    private val groupShortMovie: GroupShortMovie,
    private val personRepository: PersonRepository,
) : BaseViewModel<Unit>() {
    private val _uiState = MutableStateFlow(PersonUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedGroup(index: Int) {
        _uiState.update {
            it.copy(selectedGroup = index)
        }

        if (uiState.value.groups.isNotEmpty()) {
            val currentKey = uiState.value.groupsKeys[index]

            _uiState.update {
                it.copy(moviesFromGroup = uiState.value.groups[currentKey] ?: listOf())
            }
        }
    }

    fun getPerson(id: Int) = launchWithoutOld(GET_PERSON_JOB) {
        val res = personRepository.getPersonById(id)

        res.onSuccess { person ->
            _uiState.update {
                it.copy(personState = PersonListUIState.Success(listOf(person)))
            }

            _uiState.update {
                it.copy(factState = FactListUIState.Success(person.facts))
            }

            getGroups(person.movies)
        }
    }

    fun getMovies(personId: Int) = launchWithoutOld(GET_MOVIES_JOB) {
//        val query = listOf(
//            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
//            Constants.SORT_TYPE to Constants.SORT_DESC,
//            Constants.PERSONS_ID_FIELD to personId.toString()
//        )
//
//        val res = getMovieByFilter.execute(query)
//
//        res.onSuccess { movies ->
//
//            _uiState.update {
//                it.copy(moviesState = MovieListUIState.Success(movies))
//            }
//        }
    }

    fun getCountAwards(personId: Int) = launchWithoutOld(GET_COUNT_AWARDS) {
        if (uiState.value.countAwards == null) return@launchWithoutOld

        val params = AwardParams(id = personId)

        val res = getPersonAwardsByDate.execute(params)

        res.onSuccess { awards ->
            if (awards.isNotEmpty()) {
                _uiState.update { it.copy(countAwards = awards.size) }
            }
        }
    }

    fun getSpouses(list: List<Int>) = launchWithoutOld(GET_SPOUSES) {
        if (uiState.value.personSpouseState is PersonListUIState.Success) return@launchWithoutOld

        val temp = multiRequest(list.map { it.toString() }) {
            personRepository.getPersonById(it.toInt())
        }

        if (temp.isNotEmpty()) {
            _uiState.update {
                it.copy(personSpouseState = PersonListUIState.Success(temp))
            }
        }
    }

    private suspend fun getGroups(movies: List<ShortMovie>) {
        _uiState.update {
            it.copy(groups = groupShortMovie.execute(movies))
        }

        _uiState.update {
            it.copy(groupsKeys = uiState.value.groups.map { movie -> movie.key })
        }

        if (uiState.value.groups.isNotEmpty()) {
            val currentKey = uiState.value.groupsKeys[0]

            _uiState.update {
                it.copy(moviesFromGroup = uiState.value.groups[currentKey] ?: listOf())
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_PERSON_JOB = "get_person"
        const val GET_MOVIES_JOB = "get_movies"
        const val GET_COUNT_AWARDS = "get_awards"
        const val GET_SPOUSES = "get_spouses"
    }
}