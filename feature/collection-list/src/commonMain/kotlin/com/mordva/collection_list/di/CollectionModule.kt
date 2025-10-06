package com.mordva.collection_list.di

import com.mordva.collection_list.presentation.CollectionListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val collectionModule = module {
    viewModelOf(::CollectionListViewModel)
}