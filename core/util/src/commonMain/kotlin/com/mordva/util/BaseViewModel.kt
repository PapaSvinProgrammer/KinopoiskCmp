package com.mordva.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val jobs = mutableMapOf<String, Job>()

    protected fun launchWithoutOld(
        key: String = DEFAULT_KEY,
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        jobs.remove(key)?.cancel()

        val job = viewModelScope.launch(dispatcher + exceptionHandler) {
            block()
        }

        jobs[key] = job
        return job
    }

    protected fun cancelJob(key: String) {
        jobs.remove(key)?.cancel()
    }

    protected fun isJobActive(key: String): Boolean =
        jobs[key]?.isActive == true

    override fun onCleared() {
        jobs.values.forEach { it.cancel() }
        jobs.clear()
        super.onCleared()
    }
}

private const val DEFAULT_KEY = "default"

internal val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
    Log.d("Coroutine Error", "Unhandled exception in job '${context.job}': ${throwable.message}")
}