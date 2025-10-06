package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.movie.presentation.movie.widget.component.RatingMovieContentRow
import com.mordva.model.movie.Movie
import com.mordva.model.totalValue.Rating
import com.mordva.model.totalValue.Votes
import com.mordva.movie.presentation.movie.widget.listItem.RatingCardLarge
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.ratingCardLargeItem(movie: Movie) {
    item {
        RatingCardLarge(
            modifier = Modifier.padding(horizontal = 15.dp),
            title = stringResource(Resources.Strings.RatingKinopoisk),
            rating = movie.rating?.kp ?: 0f,
            votes = movie.votes?.kp ?: 0,
            onClick = {}
        )
    }

    item {
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