package com.mordva.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class ListViewModel<Item, AnyState>(
    initialState: AnyState
) : ViewModel() {
    private var page = 1

    private val _uiState = MutableStateFlow(ScreenListUiState<Item, AnyState>(initialState))
    val uiState: StateFlow<ScreenListUiState<Item, AnyState>> = _uiState.asStateFlow()

    abstract suspend fun getItems(page: Int): Result<List<Item>>

    protected fun updateAnyState(transform: (AnyState) -> AnyState) {
        _uiState.update { current ->
            current.copy(anyState = transform(current.anyState))
        }
    }


    fun loadItems() = launchWithoutOld(GET_ITEMS_JOB) {
        if (uiState.value.listState is ListUIState.Success) return@launchWithoutOld

        val res = getItems(page)

        res.onSuccess { items ->
            _uiState.update {
                it.copy(listState = ListUIState.Success(items))
            }
        }.onFailure { error ->
            _uiState.update {
                it.copy(listState = ListUIState.Error(error.message ?: "Unknown error"))
            }
        }
    }

    fun loadMoreItems() = launchWithoutOld(LOAD_MORE_ITEMS_JOB) {
        val currentState = uiState.value.listState
        if (currentState !is ListUIState.Success) return@launchWithoutOld

        page++

        val res = getItems(page)

        res.onSuccess { newItems ->
            val updatedList = currentState.data.toMutableList().apply {
                addAll(newItems)
            }
            _uiState.update {
                it.copy(listState = ListUIState.Success(updatedList))
            }
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

data class ScreenListUiState<Item, AnyState>(
    val anyState: AnyState,
    val listState: ListUIState<Item> = ListUIState.Loading(),
)

sealed interface ListUIState<T> {
    class Loading<T> : ListUIState<T>
    data class Success<T>(val data: List<T>) : ListUIState<T>
    data class Error<T>(val message: String) : ListUIState<T>
}
