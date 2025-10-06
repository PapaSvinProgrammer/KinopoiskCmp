package com.mordva.person.di

import com.mordva.person.domain.GroupShortMovie
import com.mordva.person.presentation.PersonViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val personModule = module {
    viewModelOf(::PersonViewModel)
    factoryOf(::GroupShortMovie)
}