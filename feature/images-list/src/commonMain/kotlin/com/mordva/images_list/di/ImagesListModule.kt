package com.mordva.images_list.di

import com.mordva.images_list.domain.GetMovieImages
import com.mordva.images_list.presentation.ImageListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val imageListViewModel = module {
    viewModelOf(::ImageListViewModel)
    factoryOf(::GetMovieImages)
}