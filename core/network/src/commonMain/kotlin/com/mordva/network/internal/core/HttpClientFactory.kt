package com.mordva.network.internal.core

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val LIMIT_API_COUNT = "20"

fun provideHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(plugin = ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                    prettyPrint = true
                }
            )
        }

        defaultRequest {
            url("https://api.kinopoisk.dev/")
            header("accept", "application/json")
            header("X-API-KEY", "94D6S0T-QWZMREW-NNMTBB7-5ZGY37K")
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
//                    Log.d("Ktor", message)
                }
            }
        }

        install(HttpRequestRetry) {
            maxRetries = 3
            retryIf { _, response ->
                response.status.value in 500..599
            }

            delayMillis { 2000L }
        }

        expectSuccess = true
    }
}