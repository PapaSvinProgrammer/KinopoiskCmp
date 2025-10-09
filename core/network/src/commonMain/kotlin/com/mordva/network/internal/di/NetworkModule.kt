package com.mordva.network.internal.di

import com.mordva.network.external.AwardService
import com.mordva.network.external.CategoryService
import com.mordva.network.external.CollectionService
import com.mordva.network.external.CommentService
import com.mordva.network.external.ImageService
import com.mordva.network.external.MovieService
import com.mordva.network.external.PersonService
import com.mordva.network.external.SeasonService
import com.mordva.network.external.StudiesService
import com.mordva.network.internal.core.provideHttpClient
import com.mordva.network.internal.service.AwardServiceImpl
import com.mordva.network.internal.service.CategoryServiceImpl
import com.mordva.network.internal.service.CollectionServiceImpl
import com.mordva.network.internal.service.CommentServiceImpl
import com.mordva.network.internal.service.ImageServiceImpl
import com.mordva.network.internal.service.MovieServiceImpl
import com.mordva.network.internal.service.PersonServiceImpl
import com.mordva.network.internal.service.SeasonServiceImpl
import com.mordva.network.internal.service.StudiesServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val networkModulePlatform: Module

val networkModule = module {
    singleOf(::AwardServiceImpl).bind<AwardService>()
    singleOf(::CategoryServiceImpl).bind<CategoryService>()
    singleOf(::CollectionServiceImpl).bind<CollectionService>()
    singleOf(::CommentServiceImpl).bind<CommentService>()
    singleOf(::MovieServiceImpl).bind<MovieService>()
    singleOf(::PersonServiceImpl).bind<PersonService>()
    singleOf(::SeasonServiceImpl).bind<SeasonService>()
    singleOf(::StudiesServiceImpl).bind<StudiesService>()
    singleOf(::ImageServiceImpl).bind<ImageService>()

    single {
        provideHttpClient(get())
    }
}