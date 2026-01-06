package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.mordva.ui.util.PosterType
import com.mordva.ui.widget.component.FadingDefaults
import com.mordva.ui.widget.component.fadingEdge

@Composable
internal fun RandomMovieBackgroundPager(
    state: PagerState,
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        state = state,
        userScrollEnabled = false,
        beyondViewportPageCount = 2,
        modifier = modifier.fillMaxWidth(),
    ) { page ->
        AsyncImage(
            model = items[page],
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fadingEdge(FadingDefaults.bottomFade)
                .aspectRatio(PosterType.STANDARD.ratio)
        )
    }
}