package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.movie.Fact

@Immutable
sealed interface FactListUIState {
    data object Loading : FactListUIState
    data class Success(val data: List<Fact>) : FactListUIState
}