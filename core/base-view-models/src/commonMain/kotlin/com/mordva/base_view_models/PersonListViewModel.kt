package com.mordva.base_view_models

import androidx.lifecycle.ViewModel
import com.mordva.domain.usecase.person.GetPersonByFilter
import com.mordva.ui.uiState.PersonListUIState
import com.mordva.util.Constants
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PersonListViewModel(
    private val getPersonByFilter: GetPersonByFilter
) : ViewModel() {
    private var page = 1

    private val _personState = MutableStateFlow(PersonListUIState.Loading as PersonListUIState)
    val personState: StateFlow<PersonListUIState> = _personState

    fun getPersons(
        queryParameters: List<Pair<String, String>>
    ) = launchWithoutOld(GET_PERSONS_JOB) {
        if (personState.value is PersonListUIState.Success) return@launchWithoutOld

        val res = getPersonByFilter.execute(queryParameters)

        res.onSuccess {
            _personState.value = PersonListUIState.Success(it)
        }
    }

    fun loadMorePersons(
        queryParameters: List<Pair<String, String>>
    ) = launchWithoutOld(LOAD_PERSONS_JOB) {
        page++

        val newQuery = queryParameters.toMutableList()
        newQuery.add(Constants.PAGE_FIELD to page.toString())

        val res = getPersonByFilter.execute(newQuery)

        res.onSuccess {
            val newRes = (personState.value as PersonListUIState.Success)
                .data
                .toMutableList()

            newRes.addAll(it)

            _personState.value = PersonListUIState.Success(newRes)
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_PERSONS_JOB = "get_persons"
        const val LOAD_PERSONS_JOB = "load_more_persons"
    }
}