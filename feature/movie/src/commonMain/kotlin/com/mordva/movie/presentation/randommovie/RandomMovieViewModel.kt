package com.mordva.movie.presentation.randommovie

import androidx.lifecycle.viewModelScope
import com.mordva.domain.model.category.ItemName
import com.mordva.domain.model.category.WatchabilityItem
import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.movie.Watchability
import com.mordva.domain.model.person.PersonMovie
import com.mordva.domain.model.season.Season
import com.mordva.domain.model.totalValue.Rating
import com.mordva.movie.presentation.randommovie.widget.RandomMovieEvent
import com.mordva.movie.presentation.randommovie.widget.RandomMovieItemState
import com.mordva.movie.presentation.randommovie.widget.RandomMovieState
import com.mordva.movie.presentation.randommovie.widget.component.RandomMoviePagerItemType
import com.mordva.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

internal class RandomMovieViewModel : BaseViewModel<RandomMovieEvent>() {
    private val pagerItemState = MutableStateFlow(RandomMoviePagerItemType.PAGER_ITEM)
    private val movieListState = MutableStateFlow<List<RandomMovieItemState>>(listOf())

    val state = combine(
        movieListState,
        pagerItemState
    ) { movieList, pagerItem ->
        RandomMovieState(
            items = movieList,
            pagerItemState = pagerItem
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RandomMovieState()
    )

    init {
        loadMovies()
    }

    fun pagerItemClicked() {
        pagerItemState.value = when (pagerItemState.value) {
            RandomMoviePagerItemType.PAGER_ITEM -> RandomMoviePagerItemType.BOTTOM_SHEET_ITEM
            RandomMoviePagerItemType.BOTTOM_SHEET_ITEM -> RandomMoviePagerItemType.PAGER_ITEM
        }
    }

    private fun loadMovies() {
        val list = mutableListOf<RandomMovieItemState>()

        movieList.forEach {
            list.add(RandomMovieItemState.Success(
                movie = it,
                images = imagesList,
                director = "Blyt",
            ))
        }

        movieListState.value = list
    }
}

private val movieAnother: List<Movie> = listOf(
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

private val imagesList = listOf(
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

private val movieList = listOf(
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