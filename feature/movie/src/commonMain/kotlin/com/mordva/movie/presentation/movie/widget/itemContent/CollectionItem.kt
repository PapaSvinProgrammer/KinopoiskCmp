package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.image.CollectionMovie
import com.mordva.ui.theme.Strings
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.listItems.CollectionListItem
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.collectionsItem(
    data: List<CollectionMovie>,
    onShowAll: () -> Unit,
    onClick: (CollectionMovie) -> Unit,
) {
    if (data.isEmpty()) return

    item(key = 12) {
        Spacer(modifier = Modifier.height(30.dp))

        TitleRow(
            title = stringResource(Strings.InLists),
            onClick = onShowAll
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier.height(230.dp)
        ) {
            items(data) { item ->
                if (item.cover?.url == null) return@items

                CollectionListItem(
                    imageUrl = item.cover?.url.toString(),
                    text = item.name.toString(),
                    modifier = Modifier
                        .width(300.dp)
                        .clickable { onClick(item) },
                )
            }
        }
    }
}
