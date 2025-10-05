package com.mordva.data.di

import com.mordva.data.AwardRepositoryImpl
import com.mordva.data.CategoryRepositoryImpl
import com.mordva.data.CollectionRepositoryImpl
import com.mordva.data.CommentRepositoryImpl
import com.mordva.data.HistoryRepositoryImpl
import com.mordva.data.MovieRepositoryImpl
import com.mordva.data.PersonRepositoryImpl
import com.mordva.data.SeasonRepositoryImpl
import com.mordva.data.StudioRepositoryImpl
import com.mordva.domain.repository.AwardRepository
import com.mordva.domain.repository.CategoryRepository
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.CommentRepository
import com.mordva.domain.repository.HistoryRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.domain.repository.SeasonRepository
import com.mordva.domain.repository.StudioRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val dataModulePlatform: Module

val dataModule = module {
    singleOf(::CategoryRepositoryImpl).bind<CategoryRepository>()
    singleOf(::CommentRepositoryImpl).bind<CommentRepository>()
    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
    singleOf(::SeasonRepositoryImpl).bind<SeasonRepository>()
    singleOf(::AwardRepositoryImpl).bind<AwardRepository>()
    singleOf(::CollectionRepositoryImpl).bind<CollectionRepository>()
    singleOf(::PersonRepositoryImpl).bind<PersonRepository>()
    singleOf(::StudioRepositoryImpl).bind<StudioRepository>()
    singleOf(::HistoryRepositoryImpl).bind<HistoryRepository>()
}