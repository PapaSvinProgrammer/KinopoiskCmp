package com.mordva.search.presentation.search_screen

import androidx.lifecycle.viewModelScope
import com.mordva.domain.model.SearchItem
import com.mordva.domain.repository.HistoryRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import com.mordva.domain.usecase.collection.model.CollectionParams
import com.mordva.search.domain.LoadMoreByName
import com.mordva.search.domain.SearchByName
import com.mordva.search.domain.model.RequestParams
import com.mordva.search.presentation.search_screen.state.SearchBodyContentState
import com.mordva.search.presentation.search_screen.state.SearchCollectionMovieContentState
import com.mordva.search.presentation.search_screen.state.SearchCollectionState
import com.mordva.search.presentation.search_screen.state.SearchListUIState
import com.mordva.search.presentation.search_screen.state.SearchMovieState
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent
import com.mordva.search.presentation.search_screen.state.SearchUiState
import com.mordva.search.presentation.search_screen.state.body
import com.mordva.search.presentation.search_screen.state.extractError
import com.mordva.search.presentation.search_screen.state.extractInit
import com.mordva.search.util.toHistory
import com.mordva.util.BaseViewModel
import com.mordva.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

internal class SearchViewModel(
    private val getCollectionByCategory: GetCollectionByCategory,
    private val searchByName: SearchByName,
    private val loadMoreByName: LoadMoreByName,
    private val historyRepository: HistoryRepository,
    private val movieRepository: MovieRepository,
) : BaseViewModel<SearchScreenEvent>() {
    private val page = MutableStateFlow(0)
    private val queryState = MutableStateFlow("")
    private val isExpandedState = MutableStateFlow(false)
    private val selectedSearchIndex = MutableStateFlow(0)
    private val movieState = MutableStateFlow<SearchMovieState>(SearchMovieState.Init)
    private val collectionState = MutableStateFlow<SearchCollectionState>(SearchCollectionState.Init)
    private val searchState = MutableStateFlow<SearchListUIState>(SearchListUIState.Loading)

    private val contentState = combine(
        movieState,
        collectionState,
        searchState,
        ::SearchCollectionMovieContentState
    )

    val uiState = combine(
        queryState,
        isExpandedState,
        selectedSearchIndex,
        contentState,
        historyRepository.getAll(),
    ) { query, isExpanded, selectedSearchIndex, content, history ->
        SearchUiState(
            query = query,
            isExpanded = isExpanded,
            selectedSearchIndex = selectedSearchIndex,
            searchHistory = history,
            searchState = content.searchState,
            bodyContentState = createSearchBodyContent(content)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState()
    )

    init {
        getTopSerials()
        getCollections()

        queryState
            .onEach { search() }
            .launchIn(viewModelScope)
    }

    fun updateQuery(text: String) {
        queryState.value = text
    }

    fun onShowSearchBar(state: Boolean) {
        queryState.value = ""
        isExpandedState.value = state
    }

    fun updateSelectedSearchIndex(index: Int) {
        selectedSearchIndex.value = index
    }

    fun onClear() {
        if (queryState.value.isEmpty()) {
            onShowSearchBar(false)
        } else {
            updateQuery("")
        }
    }

    fun search() = launchWithoutOld(SEARCH_JOB) {
        searchState.value = SearchListUIState.Loading
        page.value = 1

        val params = RequestParams(
            selectedIndex = selectedSearchIndex.value,
            q = queryState.value,
            page = page.value
        )

        searchByName.execute(params)
            .onSuccess { items ->
                searchState.value = SearchListUIState.Success(items)
            }.onFailure {
                searchState.value = SearchListUIState.Error
            }
    }

    fun loadMore() = launchWithoutOld(SEARCH_JOB) {
        page.value += 1

        val params = RequestParams(
            selectedIndex = selectedSearchIndex.value,
            q = queryState.value,
            page = page.value,
        )

        loadMoreByName.execute(params).onSuccess { items ->
            val temp = searchState.value.body() + items
            searchState.value = SearchListUIState.Success(temp)
        }
    }

    fun insertSearchHistoryItem(searchItem: SearchItem) = launchWithoutOld(INSERT_HISTORY_JOB) {
        historyRepository.insert(searchItem.toHistory())
    }

    fun deleteSearchHistoryItem(id: Int) = launchWithoutOld(DELETE_HISTORY_JOB) {
        historyRepository.delete(id)
    }

    private fun getCollections() = launchWithoutOld(GET_COLLECTIONS_JOB) {
        val params = CollectionParams(category = "Фильмы")
        val res = getCollectionByCategory.execute(params)

        res.onSuccess { collections ->
            collectionState.value = SearchCollectionState.Success(collections)
        }.onFailure {
            collectionState.value = SearchCollectionState.Error
        }
    }

    private fun getTopSerials() = launchWithoutOld(GET_SERIALS_JOB) {
        val queryParameters = listOf(
            Constants.IS_SERIES_FIELD to Constants.TRUE,
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        val res = movieRepository.getMovieByFilter(queryParameters)

        res.onSuccess { serials ->
            movieState.value = SearchMovieState.Success(serials)
        }.onFailure {
            movieState.value = SearchMovieState.Error
        }
    }

    private fun createSearchBodyContent(content: SearchCollectionMovieContentState) = when {
        content.extractInit() -> SearchBodyContentState.Loading
        content.extractError() -> SearchBodyContentState.Error
        else -> SearchBodyContentState.Success(
            collections = content.collectionState.body(),
            topSerials = content.movieState.body(),
        )
    }

    private companion object {
        const val GET_COLLECTIONS_JOB = "get_collections"
        const val GET_SERIALS_JOB = "get_top_serials"
        const val INSERT_HISTORY_JOB = "insert_search_history"
        const val DELETE_HISTORY_JOB = "delete_search_history"
        const val SEARCH_JOB = "search_movies_and_persons"
    }
}
