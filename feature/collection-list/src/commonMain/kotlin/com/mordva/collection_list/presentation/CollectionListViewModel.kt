package com.mordva.collection_list.presentation

import androidx.lifecycle.ViewModel
import com.mordva.collection_list.presentation.util.toMutableCollectionList
import com.mordva.collection_list.presentation.widget.UiState
import com.mordva.domain.usecase.collection.GetCollectionAll
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import com.mordva.domain.usecase.collection.GetCollectionBySlug
import com.mordva.domain.usecase.collection.model.CollectionParams
import com.mordva.ui.uiState.CollectionUIState
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import com.mordva.util.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class CollectionListViewModel(
    private val getCollectionAll: GetCollectionAll,
    private val getCollectionByCategory: GetCollectionByCategory,
    private val getCollectionBySlug: GetCollectionBySlug
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun getAllCollections() = launchWithoutOld(GET_ALL_JOB) {
        val page = state.value.page

        getCollectionAll.execute(page).onSuccess { collections ->
            _state.update {
                it.copy(collectionState = CollectionUIState.Success(collections))
            }
        }
    }

    fun loadMoreAllCollections() = launchWithoutOld(LOAD_ALL_JOB) {
        _state.update { it.copy(page = it.page + 1) }
        val page = state.value.page

        getCollectionAll.execute(page).onSuccess { collections ->
            val temp = state.value.toMutableCollectionList()
            temp.addAll(collections)

            _state.update {
                it.copy(collectionState = CollectionUIState.Success(temp))
            }
        }
    }

    fun getCollectionsByCategory(category: String) = launchWithoutOld(GET_BY_CATEGORY_JOB) {
        val params = CollectionParams(
            category = category
        )

        getCollectionByCategory.execute(params).onSuccess { collections ->
            _state.update {
                it.copy(collectionState = CollectionUIState.Success(collections))
            }
        }
    }

    fun loadMoreCollectionsByCategory(category: String) = launchWithoutOld(LOAD_BY_CATEGORY_JOB) {
        _state.update { it.copy(page = it.page + 1) }

        val params = CollectionParams(
            page = state.value.page,
            category = category
        )

        getCollectionByCategory.execute(params).onSuccess { collections ->
            val temp = state.value.toMutableCollectionList()
            temp.addAll(collections)

            _state.update {
                it.copy(collectionState = CollectionUIState.Success(temp))
            }
        }
    }

    fun getCollectionsByListId(list: List<String>) = launchWithoutOld(GET_BY_LIST_ID_JOB) {
        val temp = multiRequest(list) { slug ->
            getCollectionBySlug.execute(slug)
        }

        _state.update {
            it.copy(collectionState = CollectionUIState.Success(temp))
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_ALL_JOB = "get_all_collections"
        const val LOAD_ALL_JOB = "load_more_all_collections"
        const val GET_BY_CATEGORY_JOB = "get_collections_by_category"
        const val LOAD_BY_CATEGORY_JOB = "load_more_collections_by_category"
        const val GET_BY_LIST_ID_JOB = "get_collections_by_list_id"
    }
}
