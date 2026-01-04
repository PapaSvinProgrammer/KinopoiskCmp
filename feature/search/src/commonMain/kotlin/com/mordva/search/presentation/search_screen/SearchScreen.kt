package com.mordva.search.presentation.search_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.search.presentation.search_screen.state.SearchBodyContentState
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowItemContent
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowSettings
import com.mordva.search.presentation.search_screen.widget.component.SearchBarContent
import com.mordva.search.presentation.search_screen.widget.component.SearchScreenLoadingContent
import com.mordva.ui.widget.component.CustomSearchBar
import com.mordva.ui.widget.component.ErrorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel,
    eventHandler: (SearchScreenEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        CustomSearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            expanded = uiState.isExpanded,
            onExpandedChange = { viewModel.onShowSearchBar(it) },
            query = uiState.query,
            onQueryChange = { viewModel.updateQuery(it) },
            onSearch = { viewModel.onShowSearchBar(false) },
            onOpen = { viewModel.onShowSearchBar(true) },
            onClose = { viewModel.onShowSearchBar(false) },
            onSettings = { eventHandler(ShowSettings) },
            onClear = { viewModel.onClear() },
            content = {
                SearchBarContent(
                    query = uiState.query,
                    movieSearchState = uiState.searchState,
                    searchHistoryList = listOf(),
                    selectedItem = uiState.selectedSearchIndex,
                    onDeleteHistoryItem = { viewModel.deleteSearchHistoryItem(it) },
                    onClick = {
                        viewModel.insertSearchHistoryItem(it)
                        eventHandler(ShowItemContent(it))
                    },
                    onLoadMore = { viewModel.loadMore() },
                    onSelectItem = {
                        viewModel.updateSelectedSearchIndex(it)
                        viewModel.search()
                    }
                )
            },
        )

        when (val state = uiState.bodyContentState) {
            is SearchBodyContentState.Success -> ErrorScreen()

            SearchBodyContentState.Error -> ErrorScreen()
            SearchBodyContentState.Loading -> SearchScreenLoadingContent()
        }
    }
}
