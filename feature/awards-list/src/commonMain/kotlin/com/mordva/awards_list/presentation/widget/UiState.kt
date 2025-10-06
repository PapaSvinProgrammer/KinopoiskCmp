package com.mordva.awards_list.presentation.widget

import com.mordva.awards_list.presentation.widget.bottomSheet.AwardsFilterType
import com.mordva.model.person.NominationAward

internal data class UiState(
    val currentFilterType: AwardsFilterType = AwardsFilterType.BY_DATE,
    val groupAwards: List<Pair<String, List<NominationAward>>> = listOf(),
    val visibleBottomSheet: Boolean = false,
    val page: Int = 1
)