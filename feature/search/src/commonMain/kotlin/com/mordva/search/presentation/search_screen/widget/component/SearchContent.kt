package com.mordva.search.presentation.search_screen.widget.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.domain.model.SearchItem
import com.mordva.domain.model.local.History
import com.mordva.search.util.toSearchItem
import com.mordva.search.presentation.search_screen.widget.listItem.SearchHistoryMovieCard
import com.mordva.search.presentation.search_screen.widget.listItem.SearchItemCard
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.lazyComponent.EndlessLazyColumn
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchContent(
    list: List<SearchItem>,
    onClick: (SearchItem) -> Unit,
    onLoadMore: () -> Unit
) {
    EndlessLazyColumn(
        items = list,
        loadMore = onLoadMore,
    ) { _, it ->
        SearchItemCard(
            searchItem = it,
            onClick = { onClick(it) }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 110.dp))
    }
}

@Composable
fun ErrorSearchContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            modifier = Modifier.padding(vertical = 60.dp),
            text = "(^_^)",
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = stringResource(Resources.Strings.NotFound),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun LoadingSearchContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 40.dp)
        )
    }
}

@Composable
fun SearchHistoryContent(
    list: List<History>,
    onClick: (SearchItem) -> Unit,
    onRemoveClick: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { entity ->
            SearchHistoryMovieCard(
                modifier = Modifier.animateItem(),
                searchItem = entity.toSearchItem(),
                onClick = { onClick(entity.toSearchItem()) },
                onRemove = { onRemoveClick(entity.movieId) }
            )
        }
    }
}
