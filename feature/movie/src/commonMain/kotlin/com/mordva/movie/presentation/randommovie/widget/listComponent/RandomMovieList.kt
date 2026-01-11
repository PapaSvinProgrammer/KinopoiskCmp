package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.widget.component.TextTitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.MovieCard
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RandomMovieList(
    titleRes: StringResource,
    list: List<Movie>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (list.isEmpty()) return

    Column {
        TextTitleRow(
            title = stringResource(titleRes),
            modifier = modifier,
        )

        DefaultLazyRow(
            list = list,
            lastItemCard = {},
            modifier = modifier,
        ) {
            MovieCard(
                name = it.name ?: "",
                image = it.poster?.url ?: "",
                rating = it.rating?.kp,
                top250 = it.top250,
                onClick = { onClick(it.id) }
            )
        }
    }
}