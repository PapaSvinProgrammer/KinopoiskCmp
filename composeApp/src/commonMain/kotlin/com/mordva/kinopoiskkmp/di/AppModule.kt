package com.mordva.kinopoiskkmp.di

import com.mordva.domain.usecase.collection.GetCollectionAll
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::GetCollectionAll)
    factoryOf(::GetCollectionByCategory)
}