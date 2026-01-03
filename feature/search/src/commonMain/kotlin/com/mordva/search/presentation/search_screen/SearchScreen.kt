package com.mordva.search.presentation.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.mordva.search.presentation.search_screen.state.SearchBodyContentState
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowAllMovies
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowCollectionAll
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowCollectionList
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowItemContent
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowMovie
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowMovieList
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent.ShowSettings
import com.mordva.search.presentation.search_screen.util.toRenderRowItem
import com.mordva.search.presentation.search_screen.widget.component.SearchBarContent
import com.mordva.search.presentation.search_screen.widget.component.SearchScreenLoadingContent
import com.mordva.search.presentation.search_screen.widget.component.collectionCategoryListItemContent
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.component.CustomSearchBar
import com.mordva.ui.widget.component.ErrorScreen
import com.mordva.ui.widget.renderState.renderCollectionRow
import com.mordva.ui.widget.renderState.renderMovieRow

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
            is SearchBodyContentState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .navigationBarsPadding()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    renderCollectionRow(
                        title = Resources.Strings.AdviseWatch,
                        list = state.collections.toRenderRowItem(),
                        onClick = { eventHandler(ShowMovieList(it)) },
                        onShowAll = { eventHandler(ShowCollectionAll) }
                    )

                    collectionCategoryListItemContent {
                        eventHandler(ShowCollectionList(it))
                    }

                    renderMovieRow(
                        title = Resources.Strings.PopularSerials,
                        list = state.topSerials.toRenderRowItem(),
                        onClick = { eventHandler(ShowMovie(it)) },
                        onShowAll = { eventHandler(ShowAllMovies("")) }
                    )

                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }

            SearchBodyContentState.Error -> ErrorScreen()
            SearchBodyContentState.Loading -> SearchScreenLoadingContent()
        }
    }
}
