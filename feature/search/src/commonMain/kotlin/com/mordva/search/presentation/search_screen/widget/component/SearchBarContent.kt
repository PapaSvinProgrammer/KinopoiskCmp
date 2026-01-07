package com.mordva.search.presentation.search_screen.widget.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.SearchItem
import com.mordva.domain.model.local.History
import com.mordva.search.presentation.search_screen.state.SearchListUIState
import com.mordva.ui.theme.Strings
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBarContent(
    query: String,
    searchHistoryList: List<History>,
    movieSearchState: SearchListUIState,
    selectedItem: Int,
    onSelectItem: (Int) -> Unit,
    onDeleteHistoryItem: (Int) -> Unit,
    onClick: (SearchItem) -> Unit,
    onLoadMore: () -> Unit
) {
    val options = listOf(
        stringResource(Strings.Cinema),
        stringResource(Strings.Persons)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        if (query.isNotEmpty()) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                options.forEachIndexed { index, s ->
                    SegmentedButton(
                        label = { Text(text = s) },
                        icon = {},
                        selected = index == selectedItem,
                        onClick = { onSelectItem(index) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size,
                            baseShape = RoundedCornerShape(10.dp)
                        )
                    )
                }
            }
        }

        if (query.isEmpty()) {
            SearchHistoryContent(
                list = searchHistoryList,
                onClick = onClick,
                onRemoveClick = onDeleteHistoryItem
            )
        } else {
            RenderSearchResult(
                state = movieSearchState,
                onClick = onClick,
                onLoadMore = onLoadMore
            )
        }
    }
}

@Composable
private fun RenderSearchResult(
    state: SearchListUIState,
    onClick: (SearchItem) -> Unit,
    onLoadMore: () -> Unit,
) {
    when (state) {
        SearchListUIState.Error -> ErrorSearchContent()
        SearchListUIState.Loading -> LoadingSearchContent()
        is SearchListUIState.Success -> {
            SearchContent(
                list = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore,
            )
        }
    }
}