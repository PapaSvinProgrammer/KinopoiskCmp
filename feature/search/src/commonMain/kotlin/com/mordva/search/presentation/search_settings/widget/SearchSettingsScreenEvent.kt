package com.mordva.search.presentation.search_settings.widget

import com.mordva.search.presentation.search_settings_list.SearchSettingsListType

internal sealed interface SearchSettingsScreenEvent {
    data object GoBack : SearchSettingsScreenEvent
    data class ShowSearchSettingsList(val type: SearchSettingsListType) : SearchSettingsScreenEvent
}