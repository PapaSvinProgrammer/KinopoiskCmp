package com.mordva.movie.presentation.randommovie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mordva.domain.model.category.ItemName
import com.mordva.domain.model.category.WatchabilityItem
import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.movie.Watchability
import com.mordva.domain.model.person.PersonMovie
import com.mordva.domain.model.season.Season
import com.mordva.domain.model.totalValue.Rating
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.movie.presentation.randommovie.widget.component.RandomMovieBackgroundPager
import com.mordva.movie.presentation.randommovie.widget.component.RandomMoviePager
import com.mordva.movie.presentation.randommovie.widget.component.RandomMoviePagerItemState
import com.mordva.movie.presentation.randommovie.widget.component.RandomMovieTopBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun RandomMovieScreen(
    onAction: (RandomMovieAction) -> Unit
) {
    val moviePagerState = rememberPagerState(pageCount = { movieList.size })
    val backgroundPagerState = rememberPagerState(pageCount = { movieList.size })
    var itemState by remember { mutableStateOf(RandomMoviePagerItemState.PAGER_ITEM) }

    LaunchedEffect(moviePagerState.currentPage, moviePagerState.currentPageOffsetFraction) {
        backgroundPagerState.scrollToPage(
            page = moviePagerState.currentPage,
            pageOffsetFraction = moviePagerState.currentPageOffsetFraction
        )
    }

    Scaffold(
        topBar = { RandomMovieTopBar(onAction = onAction) },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            RandomMovieBackgroundPager(
                state = backgroundPagerState,
                itemState = itemState,
                items = movieList.map { it.poster?.url.toString() },
                modifier = Modifier.align(Alignment.TopCenter)
            )

            RandomMoviePager(
                state = moviePagerState,
                itemState = itemState,
                items = movieList,
                onItemClick = {
                    itemState = when (itemState) {
                        RandomMoviePagerItemState.PAGER_ITEM -> RandomMoviePagerItemState.BOTTOM_SHEET_ITEM
                        RandomMoviePagerItemState.BOTTOM_SHEET_ITEM -> RandomMoviePagerItemState.PAGER_ITEM
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

val movieAnother: List<Movie> = listOf(
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
        ),
        persons = listOf(
            PersonMovie(
                id = 1,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 2,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 3,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 4,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            )
        ),
        seasonsInfo = listOf(
            Season(
                movieId = 1,
                number = 1,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 2,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 3,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            )
        ),
        watchability = Watchability(
            items = listOf(
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                )
            )
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
        ),
        persons = listOf(
            PersonMovie(
                id = 1,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 2,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 3,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 4,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            )
        ),
        seasonsInfo = listOf(
            Season(
                movieId = 1,
                number = 1,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 2,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 3,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            )
        ),
        watchability = Watchability(
            items = listOf(
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                )
            )
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
        ),
        persons = listOf(
            PersonMovie(
                id = 1,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 2,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 3,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 4,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            )
        ),
        seasonsInfo = listOf(
            Season(
                movieId = 1,
                number = 1,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 2,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 3,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            )
        ),
        watchability = Watchability(
            items = listOf(
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                )

            )
        )
    )
)

val imagesList = listOf(
    Poster(
        url = "https://i.pinimg.com/originals/cf/6f/bf/cf6fbf2549485c919b6f488433e4d4ff.jpg",
        id = "TODO()",
        height = null,
        width = null,
        previewUrl = null
    ),
    Poster(
        url = "https://i.pinimg.com/736x/2e/5f/de/2e5fde5464acd2ccdae2f0ff24bc23c3.jpg",
        id = "TODO()",
        height = null,
        width = null,
        previewUrl = null
    ),
    Poster(
        url = "https://i.pinimg.com/originals/cf/6f/bf/cf6fbf2549485c919b6f488433e4d4ff.jpg",
        id = "TODO()",
        height = null,
        width = null,
        previewUrl = null
    ),
    Poster(
        url = "https://i.pinimg.com/736x/2e/5f/de/2e5fde5464acd2ccdae2f0ff24bc23c3.jpg",
        id = "TODO()",
        height = null,
        width = null,
        previewUrl = null
    )
)

val movieList = listOf(
    Movie(
        name = "Острые пузырьки",
        rating = Rating(kp = 5.5f),
        poster = Poster(
            url = "https://i.ebayimg.com/images/g/17YAAOSwynZmQ3hG/s-l1600.jpg",
            id = "TODO()",
            height = null,
            width = null,
            previewUrl = null
        ),
        genres = listOf(
            ItemName("Драма боевик", ""),
            ItemName("Боевик боевик", ""),
            ItemName("Длинный жанр", "")
        ),
        persons = listOf(
            PersonMovie(
                id = 1,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 2,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 3,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 4,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            )
        ),
        seasonsInfo = listOf(
            Season(
                movieId = 1,
                number = 1,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 2,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 3,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            )
        ),
        watchability = Watchability(
            items = listOf(
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                )
            )
        ),
        sequelsAndPrequels = movieAnother
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
        ),
        persons = listOf(
            PersonMovie(
                id = 1,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 2,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 3,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 4,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            )
        ),
        seasonsInfo = listOf(
            Season(
                movieId = 1,
                number = 1,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 2,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 3,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            )
        ),
        watchability = Watchability(
            items = listOf(
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                )
            )
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
        ),
        persons = listOf(
            PersonMovie(
                id = 1,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 2,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 3,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            ),
            PersonMovie(
                id = 4,
                name = "Олений пенис",
                enName = "Oleniy penis",
                photo = "https://i.pinimg.com/originals/d1/7c/f6/d17cf6e49ad95eeb27e51bde3b157fb6.jpg",
                description = "hui znaet",
                profession = "zalupa ebanay",
                enProfession = "english blyt"
            )
        ),
        seasonsInfo = listOf(
            Season(
                movieId = 1,
                number = 1,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 2,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            ),
            Season(
                movieId = 1,
                number = 3,
                name = "Season name",
                enName = "asdasd",
                episodesCount = 10,
                airDate = "",
                episodes = listOf(),
            )
        ),
        watchability = Watchability(
            items = listOf(
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                ),
                WatchabilityItem(
                    name = "asdas",
                    logo = Poster(
                        url = "https://m.media-amazon.com/images/M/MV5BOTg2M2Q1N2EtYmNmMC00NjI1LWE3YjAtYmM5ZWEyNTRmNmRjXkEyXkFqcGc@._V1_.jpg",
                        id = "TODO()",
                        height = null,
                        width = null,
                        previewUrl = null
                    ),
                    url = ""
                )

            )
        )
    )
)
