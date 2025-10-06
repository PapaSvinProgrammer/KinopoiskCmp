package com.mordva.awards_list.di

import com.mordva.awards_list.presentation.AwardListViewModel
import com.mordva.awards_list.domain.FilterAwardsByGroup
import com.mordva.awards_list.domain.GetAwards
import com.mordva.awards_list.domain.LoadMoreAwards
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val awardsListModule = module {
    viewModelOf(::AwardListViewModel)
    factoryOf(::FilterAwardsByGroup)
    factoryOf(::GetAwards)
    factoryOf(::LoadMoreAwards)
}