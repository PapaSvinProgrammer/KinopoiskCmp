package com.mordva.search.presentation.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent
import com.mordva.search.presentation.search_screen.widget.component.SearchBarContent
import com.mordva.search.presentation.search_screen.widget.component.collectionCategoryListItemContent
import com.mordva.search.presentation.search_screen.widget.component.collectionsItemContent
import com.mordva.search.presentation.search_screen.widget.component.serialsItemContent
import com.mordva.ui.widget.component.CustomSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel,
    eventHandler: (SearchScreenEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box {
        CustomSearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            expanded = uiState.isExpanded,
            onExpandedChange = { viewModel.onShowSearchBar(it) },
            query = uiState.query,
            onQueryChange = { viewModel.updateQuery(it) },
            onSearch = { viewModel.onShowSearchBar(false) },
            onOpen = { viewModel.onShowSearchBar(true) },
            onClose = { viewModel.onShowSearchBar(false) },
            onSettings = { eventHandler(SearchScreenEvent.ShowSettings) },
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
                        eventHandler(SearchScreenEvent.ShowItemContent(it))
                    },
                    onLoadMore = { viewModel.loadMore() },
                    onSelectItem = {
                        viewModel.updateSelectedSearchIndex(it)
                        viewModel.search()
                    }
                )
            },
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 140.dp)
                .navigationBarsPadding()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            collectionsItemContent(
//                state = uiState.collectionsState,
                onShowAll = { eventHandler(SearchScreenEvent.ShowCollectionAll) },
                onCollectionClick = { eventHandler(SearchScreenEvent.ShowMovieList(it)) },
            )

            collectionCategoryListItemContent {
                eventHandler(SearchScreenEvent.ShowCollectionList(it))
            }

            serialsItemContent(
//                state = uiState.topSerialsState,
                onShowAll = { eventHandler(SearchScreenEvent.ShowAllMovies(it)) },
                onMovieClick = { eventHandler(SearchScreenEvent.ShowMovie(it)) }
            )
        }
    }
}
