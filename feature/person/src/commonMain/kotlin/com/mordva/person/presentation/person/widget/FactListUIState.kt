package com.mordva.person.presentation.person.widget

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.movie.Fact

@Immutable
sealed interface FactListUIState {
    data object Loading : FactListUIState
    data class Success(val data: List<Fact>) : FactListUIState
}