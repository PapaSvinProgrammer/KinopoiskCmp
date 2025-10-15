package com.mordva.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class ListViewModel<Item>() : ViewModel() {
    private var page = 1

    private val _uiState = MutableStateFlow(ListUIState.Loading<Item>() as ListUIState<Item>)
    val uiState: StateFlow<ListUIState<Item>> = _uiState

    abstract fun getItems(page: Int): Result<List<Item>>

    fun loadItems() = launchWithoutOld(GET_ITEMS_JOB) {
        if (uiState.value is ListUIState.Success) return@launchWithoutOld

        val res = getItems(page)

        res.onSuccess { items ->
            _uiState.value = ListUIState.Success(items)
        }.onFailure { error ->
            _uiState.value = ListUIState.Error(error.message ?: "Unknown error")
        }
    }

    fun loadMoreItems() = launchWithoutOld(LOAD_MORE_ITEMS_JOB) {
        val currentState = uiState.value
        if (currentState !is ListUIState.Success) return@launchWithoutOld

        page++

        val res = getItems(page)

        res.onSuccess { newItems ->
            val updatedList = currentState.data.toMutableList().apply {
                addAll(newItems)
            }
            _uiState.value = ListUIState.Success(updatedList)
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_ITEMS_JOB = "get_items"
        const val LOAD_MORE_ITEMS_JOB = "load_more_items"
    }
}

sealed class ListUIState<T> {
    class Loading<T> : ListUIState<T>()
    data class Success<T>(val data: List<T>) : ListUIState<T>()
    data class Error<T>(val message: String) : ListUIState<T>()
}
