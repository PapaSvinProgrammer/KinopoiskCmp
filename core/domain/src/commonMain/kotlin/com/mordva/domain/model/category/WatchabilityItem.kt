package com.mordva.domain.model.category

import com.mordva.domain.model.image.Poster

data class WatchabilityItem(
    val name: String?,
    val logo: Poster?,
    val url: String?,
)