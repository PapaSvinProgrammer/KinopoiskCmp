package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.person.Person

@Immutable
sealed interface PersonListUIState {
    data class Success(val data: List<Person>) : PersonListUIState
    data object Loading : PersonListUIState
}