package com.mordva.collection_list.presentation

import com.mordva.collection_list.presentation.util.toMutableCollectionList
import com.mordva.collection_list.presentation.widget.CollectionListUIState
import com.mordva.collection_list.presentation.widget.UiState
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.usecase.collection.GetCollectionAll
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import com.mordva.domain.usecase.collection.model.CollectionParams
import com.mordva.util.BaseViewModel
import com.mordva.util.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class CollectionListViewModel(
    private val getCollectionAll: GetCollectionAll,
    private val getCollectionByCategory: GetCollectionByCategory,
    private val collectionRepository: CollectionRepository,
) : BaseViewModel<Unit>() {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun getAllCollections() = launchWithoutOld(GET_ALL_JOB) {
        val page = state.value.page

        getCollectionAll.execute(page).onSuccess { collections ->
            _state.update {
                it.copy(collectionState = CollectionListUIState.Success(collections))
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
                it.copy(collectionState = CollectionListUIState.Success(temp))
            }
        }
    }

    fun getCollectionsByCategory(category: String) = launchWithoutOld(GET_BY_CATEGORY_JOB) {
        val params = CollectionParams(
            category = category
        )

        getCollectionByCategory.execute(params).onSuccess { collections ->
            _state.update {
                it.copy(collectionState = CollectionListUIState.Success(collections))
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
                it.copy(collectionState = CollectionListUIState.Success(temp))
            }
        }
    }

    fun getCollectionsByListId(list: List<String>) = launchWithoutOld(GET_BY_LIST_ID_JOB) {
        val temp = multiRequest(list) { slug ->
            collectionRepository.getCollectionBySlug(slug)
        }

        _state.update {
            it.copy(collectionState = CollectionListUIState.Success(temp))
        }
    }

    private companion object {
        const val GET_ALL_JOB = "get_all_collections"
        const val LOAD_ALL_JOB = "load_more_all_collections"
        const val GET_BY_CATEGORY_JOB = "get_collections_by_category"
        const val LOAD_BY_CATEGORY_JOB = "load_more_collections_by_category"
        const val GET_BY_LIST_ID_JOB = "get_collections_by_list_id"
    }
}
