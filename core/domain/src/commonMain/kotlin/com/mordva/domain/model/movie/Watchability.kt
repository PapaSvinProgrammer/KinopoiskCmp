package com.mordva.domain.model.movie

import com.mordva.domain.model.category.WatchabilityItem

data class Watchability(
    val items: List<WatchabilityItem>
)