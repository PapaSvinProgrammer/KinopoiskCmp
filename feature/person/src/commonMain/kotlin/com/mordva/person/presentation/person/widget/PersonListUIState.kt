package com.mordva.person.presentation.person.widget

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.person.Person

@Immutable
sealed interface PersonListUIState {
    data class Success(val data: List<Person>) : PersonListUIState
    data object Loading : PersonListUIState
}