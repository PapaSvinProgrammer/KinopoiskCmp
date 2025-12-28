package com.mordva.search.di

import com.mordva.search.domain.LoadMoreByName
import com.mordva.search.domain.SearchByName
import com.mordva.search.domain.SearchSettingsItemListManager
import com.mordva.search.presentation.search_screen.SearchViewModel
import com.mordva.search.presentation.search_settings.SearchSettingsViewModel
import com.mordva.search.presentation.search_settings_list.SearchSettingsListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::SearchSettingsViewModel)
    viewModelOf(::SearchSettingsListViewModel)
    factoryOf(::LoadMoreByName)
    factoryOf(::SearchByName)
    factoryOf(::SearchSettingsItemListManager)
}