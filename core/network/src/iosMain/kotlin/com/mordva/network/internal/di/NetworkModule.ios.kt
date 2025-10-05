package com.mordva.network.internal.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val networkModulePlatform = module {
    single<HttpClientEngine> { Darwin.create() }
}