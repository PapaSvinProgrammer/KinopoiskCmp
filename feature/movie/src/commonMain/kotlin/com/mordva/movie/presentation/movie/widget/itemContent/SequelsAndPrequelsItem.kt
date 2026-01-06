package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.Movie
import com.mordva.ui.theme.Strings
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.MovieCard
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.sequelsAndPrequelsItem(
    list: List<Movie>,
    onClick: (Movie) -> Unit,
) {
    item(key = 15) {
        if (list.isEmpty()) return@item

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(Strings.SequalsAndPrequals),
            fontSize = Typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )

        DefaultLazyRow(
            list = list,
            lastItemCard = {},
        ) {
            MovieCard(
                name = it.name ?: "",
                image = it.poster?.url ?: "",
                rating = it.rating?.kp,
                top250 = it.top250,
                onClick = { onClick(it) }
            )
        }
    }
}
