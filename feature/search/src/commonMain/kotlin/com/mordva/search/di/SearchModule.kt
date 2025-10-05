package com.mordva.search.di

import com.mordva.search.domain.LoadMoreByName
import com.mordva.search.domain.SearchByName
import com.mordva.search.presentation.searchResult.SearchResultViewModel
import com.mordva.search.presentation.searchScreen.SearchViewModel
import com.mordva.search.presentation.searchSettings.SearchSettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::SearchResultViewModel)
    viewModelOf(::SearchSettingsViewModel)
    factoryOf(::LoadMoreByName)
    factoryOf(::SearchByName)
}