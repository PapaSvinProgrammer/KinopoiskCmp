package com.mordva.domain.model.person

import com.mordva.domain.model.movie.ShortMovie

data class NominationAward(
    val nomination: Nomination?,
    val winning: Boolean?,
    val personId: Int?,
    val movie: ShortMovie?
)
