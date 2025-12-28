package com.mordva.search.navigation

import com.mordva.search.presentation.search_settings_list.SearchSettingsListType
import kotlinx.serialization.Serializable

@Serializable
internal data object SearchRoute

@Serializable
internal data object SearchSettingsRoute

@Serializable
internal data class SearchSettingsListRoute(
    val type: SearchSettingsListType
)