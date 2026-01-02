package com.mordva.search.presentation.search_screen.widget.component

import androidx.compose.foundation.lazy.LazyListScope
import com.mordva.domain.model.image.CollectionMovie
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.collectionsItemContent(
//    state: CollectionListState,
    onShowAll: () -> Unit,
    onCollectionClick: (CollectionMovie) -> Unit,
) {
    item {
//        RenderCollectionStateRow(
//            state = state,
//            title = stringResource(Resources.Strings.AdviseWatch),
//            onClick = onCollectionClick,
//            onShowAll = onShowAll
//        )
    }
}