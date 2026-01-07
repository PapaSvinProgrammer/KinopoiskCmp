package com.mordva.network.internal.core

import com.mordva.network.internal.util.ClientException
import com.mordva.network.internal.util.ModelSerializationException
import com.mordva.network.internal.util.ServerException
import com.mordva.network.internal.util.UnknownException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

internal suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.success(response.body<T>())
            } catch (e: Exception) {
                Result.failure(ModelSerializationException())
            }
        }

        in 400..499 -> Result.failure(ClientException())
        in 500..599 -> Result.failure(ServerException())
        else -> Result.failure(UnknownException())
    }
}