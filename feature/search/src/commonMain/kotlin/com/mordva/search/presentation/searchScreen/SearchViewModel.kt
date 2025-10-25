package com.mordva.search.presentation.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mordva.domain.repository.HistoryRepository
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import com.mordva.domain.usecase.collection.model.CollectionParams
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.model.SearchItem
import com.mordva.search.domain.LoadMoreByName
import com.mordva.search.domain.SearchByName
import com.mordva.search.domain.model.RequestParams
import com.mordva.search.presentation.searchScreen.widget.UiState
import com.mordva.ui.uiState.CollectionListUIState
import com.mordva.ui.uiState.MovieListUIState
import com.mordva.ui.uiState.SearchListUIState
import com.mordva.util.Constants
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class SearchViewModel(
    private val getCollectionByCategory: GetCollectionByCategory,
    private val getMovieByFilter: GetMovieByFilter,
    private val searchByName: SearchByName,
    private val loadMoreByName: LoadMoreByName,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState
            .map { it.query }
            .distinctUntilChanged()
            .onEach { search() }
            .launchIn(viewModelScope)
    }

    fun updateQuery(text: String) {
        _uiState.update { it.copy(query = text) }
    }

    fun onShowSearchBar(state: Boolean) {
        if (!state) {
            _uiState.update { it.copy(query = "") }
            _uiState.update { it.copy(searchState = SearchListUIState.Error) }
        }

        _uiState.update {
            it.copy(isExpanded = state)
        }
    }

    fun updateSelectedSearchIndex(index: Int) {
        _uiState.update {
            it.copy(selectedSearchIndex = index)
        }
    }

    fun onClear() {
        if (uiState.value.query.isEmpty()) {
            onShowSearchBar(false)
        } else {
            updateQuery("")
        }
    }

    fun getCollections() = launchWithoutOld(GET_COLLECTIONS_JOB) {
        if (uiState.value.collectionsState is CollectionListUIState.Success) return@launchWithoutOld

        val params = CollectionParams(category = "Фильмы")

        val res = getCollectionByCategory.execute(params)

        res.onSuccess { collections ->
            _uiState.update {
                it.copy(collectionsState = CollectionListUIState.Success(collections))
            }
        }
    }

    fun getTopSerials() = launchWithoutOld(GET_SERIALS_JOB) {
        if (uiState.value.topSerialsState is MovieListUIState.Success) return@launchWithoutOld

        val queryParameters = listOf(
            Constants.IS_SERIES_FIELD to Constants.TRUE,
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        val res = getMovieByFilter.execute(queryParameters)

        res.onSuccess { serials ->
            _uiState.update {
                it.copy(topSerialsState = MovieListUIState.Success(serials))
            }
        }
    }

    fun search() = launchWithoutOld(SEARCH_JOB) {
        _uiState.update {
            it.copy(
                searchState = SearchListUIState.Loading,
                page = 1
            )
        }

        val params = RequestParams(
            selectedIndex = uiState.value.selectedSearchIndex,
            q = uiState.value.query,
            page = uiState.value.page
        )

        searchByName.execute(params).onSuccess { items ->
            _uiState.update {
                it.copy(searchState = SearchListUIState.Success(items))
            }
        }
    }

    fun loadMore() = launchWithoutOld(SEARCH_JOB) {
        _uiState.update { it.copy(page = it.page + 1) }

        val params = RequestParams(
            selectedIndex = uiState.value.selectedSearchIndex,
            q = uiState.value.query,
            page = uiState.value.page
        )

        loadMoreByName.execute(params).onSuccess { items ->
            val temp = (uiState.value.searchState as SearchListUIState.Success)
                .data
                .toMutableList()

            temp.addAll(items)

            _uiState.update {
                it.copy(searchState = SearchListUIState.Success(temp))
            }
        }
    }

    fun insertSearchHistoryItem(searchItem: SearchItem) = launchWithoutOld(INSERT_HISTORY_JOB) {
//        historyRepository.insert(searchItem.toHistory())
    }

    fun deleteSearchHistoryItem(id: Int) = launchWithoutOld(DELETE_HISTORY_JOB) {
        historyRepository.delete(id)
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_COLLECTIONS_JOB = "get_collections"
        const val GET_SERIALS_JOB = "get_top_serials"
        const val INSERT_HISTORY_JOB = "insert_search_history"
        const val DELETE_HISTORY_JOB = "delete_search_history"
        const val SEARCH_JOB = "search_movies_and_persons"
    }
}
