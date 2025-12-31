package com.mordva.search.presentation.search_settings_list

import com.mordva.model.category.ItemName
import com.mordva.search.domain.SearchSettingsItemListManager
import com.mordva.util.BaseViewModel

internal class SearchSettingsListViewModel(
    private val searchSettingsItemListManager: SearchSettingsItemListManager,
) : BaseViewModel<Unit>() {
    val state = searchSettingsItemListManager.state

    fun reset() {
        searchSettingsItemListManager.reset()
    }

    fun updateQuery(text: String) {
        searchSettingsItemListManager.updateQuery(text)
    }

    fun toggleItem(item: ItemName) {
        searchSettingsItemListManager.toggleItem(item)
    }
}