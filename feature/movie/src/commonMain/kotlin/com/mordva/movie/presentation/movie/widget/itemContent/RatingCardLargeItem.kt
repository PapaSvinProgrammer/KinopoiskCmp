package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.totalValue.Rating
import com.mordva.domain.model.totalValue.Votes
import com.mordva.movie.presentation.movie.widget.component.RatingMovieContentRow
import com.mordva.movie.presentation.movie.widget.listItem.RatingCardLarge
import com.mordva.ui.theme.Strings
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.ratingCardLargeItem(
    movie: Movie,
    onClick: () -> Unit,
) {
    item(key = 5) {
        RatingCardLarge(
            modifier = Modifier.padding(horizontal = 15.dp),
            title = stringResource(Strings.RatingKinopoisk),
            rating = movie.rating?.kp ?: 0f,
            votes = movie.votes?.kp ?: 0,
            onClick = onClick,
        )
    }

    item(key = 6) {
        movie.rating?.let {
            RatingMovieContentRow(
                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
                rating = movie.rating ?: Rating(),
                votes = movie.votes ?: Votes()
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}