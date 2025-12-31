package com.mordva.movie.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
internal fun rememberCollapsedState(scrollState: LazyListState) = remember {
    derivedStateOf {
        if (scrollState.firstVisibleItemIndex == 0) {
            scrollState.firstVisibleItemScrollOffset > 600
        } else {
            true
        }
    }
}