package com.mordva.ui.widget.renderState

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.Fact
import com.mordva.ui.theme.Typography
import com.mordva.ui.uiState.FactListUIState
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.FactCard
import com.mordva.ui.widget.shimmer.ShimmerFactRow

@Composable
fun RenderFactStateRow(
    state: FactListUIState,
    title: String,
    onClick: (Fact) -> Unit
) {
    when (state) {
        FactListUIState.Loading -> ShimmerFactRow()
        is FactListUIState.Success -> {
            MainFactRowContent(
                list = state.data,
                title = title,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MainFactRowContent(
    list: List<Fact>,
    title: String,
    onClick: (Fact) -> Unit
) {
    Text(
        text = title,
        fontSize = Typography.titleMedium.fontSize,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(15.dp)
    )

    DefaultLazyRow(
        list = list,
        key = { it.value },
        lastItemCard = {}
    ) {
        FactCard(
            text = it.value,
            isSpoiler = it.spoiler ?: false,
            onClick = { onClick(it) }
        )
    }
}