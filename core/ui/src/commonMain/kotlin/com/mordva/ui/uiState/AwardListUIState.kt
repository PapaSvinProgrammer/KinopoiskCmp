package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.person.NominationAward

@Immutable
sealed interface AwardListUIState {
    data object Loading : AwardListUIState
    data class Success(val data: List<NominationAward>) : AwardListUIState
}