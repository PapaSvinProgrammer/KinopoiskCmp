package com.mordva.search.presentation.search_screen.widget.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.widget.shimmer.ShimmerCollectionRow
import com.mordva.ui.widget.shimmer.ShimmerMovieRow

@Composable
internal fun SearchScreenLoadingContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        ShimmerCollectionRow()
        Spacer(modifier = Modifier.height(DsSpacer.M16))
        ShimmerMovieRow()
        Spacer(modifier = Modifier.height(DsSpacer.M16))
        ShimmerMovieRow()
    }
}