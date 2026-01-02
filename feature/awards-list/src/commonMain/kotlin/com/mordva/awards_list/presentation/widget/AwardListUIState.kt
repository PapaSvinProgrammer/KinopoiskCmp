package com.mordva.awards_list.presentation.widget

import androidx.compose.runtime.Immutable
import com.mordva.domain.model.person.NominationAward

@Immutable
sealed interface AwardListUIState {
    data object Loading : AwardListUIState
    data class Success(val data: List<NominationAward>) : AwardListUIState
}