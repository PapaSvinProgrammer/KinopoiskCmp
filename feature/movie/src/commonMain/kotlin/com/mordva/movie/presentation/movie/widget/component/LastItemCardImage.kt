package com.mordva.movie.presentation.movie.widget.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mordva.ui.widget.listItems.LastItemCard

@Composable
internal fun LastItemCardImage(
    onClick: () -> Unit
) {
    LastItemCard(
        width = 200.dp,
        height = 160.dp,
        onClick = onClick
    )
}