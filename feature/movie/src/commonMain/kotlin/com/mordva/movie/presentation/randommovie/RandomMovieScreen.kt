package com.mordva.movie.presentation.randommovie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarDefaults.ScreenOffset
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mordva.domain.model.category.ItemName
import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.totalValue.Rating
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.movie.presentation.randommovie.widget.component.RandomMovieBackgroundPager
import com.mordva.movie.presentation.randommovie.widget.component.RandomMoviePager
import com.mordva.movie.presentation.randommovie.widget.component.RandomMovieTopBar
import com.mordva.ui.theme.Icons
import org.jetbrains.compose.resources.painterResource
import kotlin.collections.map

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun RandomMovieScreen() {
    val moviePagerState = rememberPagerState(pageCount = { movieList.size })
    val backgroundPagerState = rememberPagerState(pageCount = { movieList.size })

    LaunchedEffect(moviePagerState.currentPage, moviePagerState.currentPageOffsetFraction) {
        backgroundPagerState.scrollToPage(
            page = moviePagerState.currentPage,
            pageOffsetFraction = moviePagerState.currentPageOffsetFraction
        )
    }

    Scaffold(
        topBar = { RandomMovieTopBar(onAction = {}) },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            RandomMovieBackgroundPager(
                state = backgroundPagerState,
                items = movieList.map { it.poster?.url.toString() },
                modifier = Modifier.align(Alignment.TopCenter)
            )

            RandomMoviePager(
                state = moviePagerState,
                items = movieList,
                modifier = Modifier.align(Alignment.BottomCenter),
            )

            RandomMovieToolbar(
                onAction = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -ScreenOffset)
                    .zIndex(1f)
                    .navigationBarsPadding()
                    .padding(bottom = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun RandomMovieToolbar(
    modifier: Modifier = Modifier,
    onAction: (RandomMovieAction) -> Unit,
) {
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()

    HorizontalFloatingToolbar(
        expanded = true,
        colors = vibrantColors,
        modifier = modifier,
    ) {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                painter = painterResource(Icons.BookmarkAdd),
                contentDescription = "Localized description"
            )
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                painter = painterResource(Icons.BookmarkAdd),
                contentDescription = "Localized description"
            )
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                painter = painterResource(Icons.BookmarkAdd),
                contentDescription = "Localized description",
            )
        }
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                painter = painterResource(Icons.BookmarkAdd),
                contentDescription = "Localized description",
            )
        }
    }
}

val movieList = listOf(
    Movie(
        name = "Острые пузырьки",
        rating = Rating(kp = 5.5f),
        poster = Poster(
            url = "https://avatars.mds.yandex.net/i?id=6585f3329576ab9bfcced8caa30ed2cdea00854c-5232907-images-thumbs&n=13",
            id = "TODO()",
            height = null,
            width = null,
            previewUrl = null
        ),
        genres = listOf(
            ItemName("Драма боевик", ""),
            ItemName("Боевик боевик", ""),
            ItemName("Длинный жанр", "")
        )
    ),
    Movie(
        name = "Острые пузырьки",
        rating = Rating(kp = 5.5f),
        poster = Poster(
            url = "https://avatars.mds.yandex.net/i?id=426e712fc7353412525836659217750e_l-5026463-images-thumbs&n=13",
            id = "TODO()",
            height = null,
            width = null,
            previewUrl = null
        ),
        genres = listOf(
            ItemName("Драма боевик", ""),
            ItemName("Боевик боевик", ""),
            ItemName("Длинный жанр", "")
        )
    ),
    Movie(
        name = "Острые пузырьки",
        rating = Rating(kp = 5.5f),
        poster = Poster(
            url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
            id = "TODO()",
            height = null,
            width = null,
            previewUrl = null
        ),
        genres = listOf(
            ItemName("Драма боевик", ""),
            ItemName("Боевик боевик", ""),
            ItemName("Длинный жанр", "")
        )
    )
)
