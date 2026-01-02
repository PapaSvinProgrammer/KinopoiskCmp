package com.mordva.search.presentation.search_settings.widget

import com.mordva.domain.model.category.ItemName

internal data class SearchSettingsListState(
    val query: String = "",
    val result: List<ItemName> = listOf(),
    val checked: Map<ItemName, Boolean> = mapOf(),
)