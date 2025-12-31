package com.mordva.awards_list.domain.model

import com.mordva.awards_list.presentation.widget.bottomSheet.AwardsFilterType

internal data class RequestParams(
    val id: Int,
    val page: Int,
    val isMovie: Boolean,
    val filterType: AwardsFilterType
)
