package com.mordva.data.mapper

import com.mordva.domain.model.category.ItemName
import com.mordva.domain.model.category.WatchabilityItem
import com.mordva.network.external.model.category.ItemNameDto
import com.mordva.network.external.model.category.WatchabilityItemDto

internal fun ItemNameDto.toDomain(): ItemName {
    return ItemName(
        name = this.name,
        slug = this.slug
    )
}

internal fun List<ItemNameDto>.toDomain() = map { it.toDomain() }

internal fun WatchabilityItemDto.toDomain(): WatchabilityItem {
    return WatchabilityItem(
        name = this.name,
        logo = this.logo?.toDomain(),
        url = this.url
    )
}