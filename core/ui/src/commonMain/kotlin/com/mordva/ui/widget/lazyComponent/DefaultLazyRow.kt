package com.mordva.ui.widget.lazyComponent

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> DefaultLazyRow(
    list: List<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 15.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(10.dp),
    key: ((item: T) -> Any)? = null,
    lastItemCard: (@Composable () -> Unit)? = null,
    content: @Composable (T) -> Unit
) {
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(listState, SnapPosition.Start)

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        contentPadding = contentPadding,
        state = listState,
        flingBehavior = flingBehavior
    ) {
        items(
            items = list,
            key = key
        ) {
            content(it)
        }

        lastItemCard?.let {
            item { it.invoke() }
        }
    }
}