package com.mordva.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlin.onSuccess

suspend fun <ResponseParams, RequestParams> multiRequest(
    list: List<RequestParams>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    execute: suspend (RequestParams) -> Result<ResponseParams>
): List<ResponseParams> {
    val tasks = mutableListOf<Deferred<Result<ResponseParams>>>()

    list.forEach { id ->
        val task = CoroutineScope(dispatcher).async {
            execute(id)
        }

        tasks.add(task)
    }

    val result = mutableListOf<ResponseParams>()

    tasks.awaitAll().forEach {
        it.onSuccess { data ->
            result.add(data)
        }
    }

    return result
}

suspend fun <ResponseParams, RequestParams> multiRequestWithResult(
    list: List<RequestParams>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    execute: suspend (RequestParams) -> Result<ResponseParams>
): List<Result<ResponseParams>> {
    val tasks = mutableListOf<Deferred<Result<ResponseParams>>>()

    list.forEach { id ->
        val task = CoroutineScope(dispatcher).async {
            execute(id)
        }

        tasks.add(task)
    }

    val result = mutableListOf<Result<ResponseParams>>()

    tasks.awaitAll().forEach {
        result.add(it)
    }

    return result
}

suspend fun <ResponseParams> multiRequest(
    count: Int,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    execute: suspend () -> Result<ResponseParams>
): List<ResponseParams> {
    val tasks = mutableListOf<Deferred<Result<ResponseParams>>>()

    repeat(count) {
        val task = CoroutineScope(dispatcher).async {
            execute()
        }

        tasks.add(task)
    }

    val result = mutableListOf<ResponseParams>()

    tasks.awaitAll().forEach {
        it.onSuccess { data ->
            result.add(data)
        }
    }

    return result
}

suspend fun <ResponseParams> multiRequestWithResult(
    count: Int,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    execute: suspend () -> Result<ResponseParams>
): List<Result<ResponseParams>> {
    val tasks = mutableListOf<Deferred<Result<ResponseParams>>>()

    repeat(count) {
        val task = CoroutineScope(dispatcher).async {
            execute()
        }

        tasks.add(task)
    }

    val result = mutableListOf<Result<ResponseParams>>()

    tasks.awaitAll().forEach {
        result.add(it)
    }

    return result
}