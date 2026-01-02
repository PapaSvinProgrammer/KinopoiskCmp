package com.mordva.ui.widget.renderState

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.CollectionCard
import com.mordva.ui.widget.listItems.MovieCard
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

fun <RENDER_ITEM : Any> LazyListScope.renderStateRow(
    title: StringResource,
    list: List<RENDER_ITEM>,
    onShowAll: () -> Unit,
    lastItemContent: (@Composable () -> Unit)? = null,
    itemContent: @Composable (RENDER_ITEM) -> Unit,
) {
    item {
        TitleRow(
            title = stringResource(title),
            onClick = onShowAll
        )

        DefaultLazyRow(
            list = list,
            lastItemCard = { lastItemContent?.invoke() },
            content = { itemContent(it) }
        )
    }
}

fun <ITEM_ID : Any> LazyListScope.renderCollectionRow(
    title: StringResource,
    list: List<RenderStateRowItemCollection<ITEM_ID>>,
    onClick: (ITEM_ID) -> Unit,
    onShowAll: () -> Unit
) {
    renderStateRow(
        title = title,
        list = list,
        onShowAll = onShowAll,
    ) {
        CollectionCard(
            image = it.image,
            title = it.title,
            onClick = { onClick(it.id) }
        )
    }
}


fun <ITEM_ID : Any> LazyListScope.renderMovieRow(
    title: StringResource,
    list: List<RenderStateRowItemMovie<ITEM_ID>>,
    onClick: (ITEM_ID) -> Unit,
    onShowAll: () -> Unit
) {
    renderStateRow(
        title = title,
        list = list,
        onShowAll = onShowAll,
    ) {
        MovieCard(
            name = it.title,
            image = it.image,
            rating = it.rating,
            top250 = it.top250,
            onClick = { onClick(it.id) }
        )
    }
}
