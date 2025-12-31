package com.mordva.home.di

import com.mordva.home.domain.GetMoviesByCollection
import com.mordva.home.domain.GetMoviesByCompany
import com.mordva.home.domain.GetMoviesByGenre
import com.mordva.home.presentation.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
    factoryOf(::GetMoviesByCollection)
    factoryOf(::GetMoviesByCompany)
    factoryOf(::GetMoviesByGenre)
}