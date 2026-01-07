package com.mordva.network.internal.core

import com.mordva.network.internal.util.ModelSerializationException
import com.mordva.network.internal.util.NoInternetException
import com.mordva.network.internal.util.UnknownException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException

internal suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.failure(NoInternetException())
    } catch (e: SerializationException) {
        return Result.failure(ModelSerializationException())
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        return Result.failure(UnknownException())
    }

    return responseToResult(response)
}