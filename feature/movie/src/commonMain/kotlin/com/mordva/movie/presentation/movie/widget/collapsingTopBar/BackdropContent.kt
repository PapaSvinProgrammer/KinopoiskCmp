package com.mordva.movie.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.component.FadingDefaults
import com.mordva.ui.widget.component.fadingEdge
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun BackdropContent(scrollState: LazyListState, movie: Movie) {
    val alpha by remember {
        derivedStateOf {
            if (scrollState.firstVisibleItemIndex > 0) {
                0f
            } else {
                1f - (scrollState.firstVisibleItemScrollOffset / 600f).coerceIn(0f, 1f)
            }
        }
    }

    if (movie.backdrop?.url.isCorrectUrl() && movie.logo?.url.isCorrectUrl()) {
        AsyncImage(
            model = movie.backdrop?.url,
            error = painterResource(Resources.Icons.Movie),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fadingEdge(FadingDefaults.bottomFade)
                .height(350.dp)
                .graphicsLayer { this.alpha = alpha }
        )
    }
}

fun String?.isCorrectUrl(): Boolean {
    return this != null
}