package com.mordva.search.presentation.searchScreen

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
import com.mordva.model.SearchItem
import com.mordva.model.image.CollectionMovie
import com.mordva.model.movie.Movie
import com.mordva.search.presentation.searchScreen.widget.component.SearchBarContent
import com.mordva.search.presentation.searchScreen.widget.component.collectionCategoryListItemContent
import com.mordva.search.presentation.searchScreen.widget.component.collectionsItemContent
import com.mordva.search.presentation.searchScreen.widget.component.serialsItemContent
import com.mordva.ui.widget.component.CustomSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel,
    onSettings: () -> Unit,
    onItemContent: (SearchItem) -> Unit,
    onCollectionShowAll: () -> Unit,
    onNavigateToMovieList: (CollectionMovie) -> Unit,
    onNavigateToCollectionList: (String) -> Unit,
    onMovieClick: (Movie) -> Unit,
    onMovieShowAllClick: (String) -> Unit,
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
            onSettings = onSettings,
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
                        onItemContent(it)
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
                state = uiState.collectionsState,
                get = { viewModel.getCollections() },
                onShowAll = onCollectionShowAll,
                onCollectionClick = onNavigateToMovieList,
            )

            collectionCategoryListItemContent(onNavigateToCollectionList)

            serialsItemContent(
                state = uiState.topSerialsState,
                get = { viewModel.getTopSerials() },
                onShowAll = onMovieShowAllClick,
                onMovieClick = onMovieClick
            )
        }
    }
}
