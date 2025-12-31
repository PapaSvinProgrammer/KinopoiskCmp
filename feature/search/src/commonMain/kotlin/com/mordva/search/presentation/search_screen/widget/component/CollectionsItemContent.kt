package com.mordva.search.presentation.search_screen.widget.component

import androidx.compose.foundation.lazy.LazyListScope
import com.mordva.model.image.CollectionMovie
import com.mordva.ui.theme.Resources
import com.mordva.ui.uiState.CollectionListUIState
import com.mordva.ui.widget.renderState.RenderCollectionStateRow
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.collectionsItemContent(
    state: CollectionListUIState,
    onShowAll: () -> Unit,
    onCollectionClick: (CollectionMovie) -> Unit,
) {
    item {
        RenderCollectionStateRow(
            state = state,
            title = stringResource(Resources.Strings.AdviseWatch),
            onClick = onCollectionClick,
            onShowAll = onShowAll
        )
    }
}