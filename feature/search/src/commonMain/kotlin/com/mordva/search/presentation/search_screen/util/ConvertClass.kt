package com.mordva.search.presentation.search_screen.util

import com.mordva.domain.model.SearchItem
import com.mordva.domain.model.image.CollectionMovie
import com.mordva.domain.model.local.History
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.person.Person
import com.mordva.domain.model.totalValue.ReleaseYears
import com.mordva.ui.util.ConvertData
import com.mordva.ui.widget.renderState.RenderStateRowItemCollection
import com.mordva.ui.widget.renderState.RenderStateRowItemMovie
import kotlin.jvm.JvmName

internal fun SearchItem.toHistory(): History {
    return History(
        movieId = this.id,
        name = this.name,
        alternativeName = this.alternativeName,
        year = this.year,
        start = this.releaseYears.start,
        end = this.releaseYears.end,
        poster = this.poster,
        isMovie = this.isMovie
    )
}

internal fun Movie.toSearchItem(): SearchItem {
    return SearchItem(
        id = this.id,
        isMovie = true,
        name = this.name ?: "",
        alternativeName = ConvertData.getAlternativeNameForMovie(
            alternativeName = alternativeName,
            year = year,
            genres = genres.map { it.name },
            start = releaseYears.firstOrNull()?.start,
            end = releaseYears.firstOrNull()?.end
        ),
        year = this.year ?: 0,
        releaseYears = this.releaseYears.firstOrNull() ?: ReleaseYears(null, null),
        poster = this.poster?.url ?: "",
        rating = this.rating?.kp ?: 0f
    )
}

internal fun Person.toSearchItem(): SearchItem {
    return SearchItem(
        id = this.id,
        isMovie = false,
        name = this.name ?: "",
        alternativeName = this.enName ?: "",
        year = this.age ?: 0,
        releaseYears = ReleaseYears(null, null),
        poster = this.photo ?: "",
        rating = 0f
    )
}

@JvmName(name = "movieToSearchItemList")
internal fun List<Movie>.toSearchItemList(): List<SearchItem> {
    val res = ArrayList<SearchItem>()

    this.forEach {
        res.add(it.toSearchItem())
    }

    return res
}

@JvmName(name = "personToSearchItemList")
internal fun List<Person>.toSearchItemList(): List<SearchItem> {
    val res = ArrayList<SearchItem>()

    this.forEach {
        res.add(it.toSearchItem())
    }

    return res
}

internal fun History.toSearchItem(): SearchItem {
    val releaseYears = ReleaseYears(
        start = this.start,
        end = this.end
    )

    return SearchItem(
        id = this.movieId,
        name = this.name,
        alternativeName = this.alternativeName,
        year = this.year,
        releaseYears = releaseYears,
        poster = this.poster,
        isMovie = this.isMovie,
        rating = 0f
    )
}

@JvmName("listCollectionMovieToRenderItemCollection")
internal fun List<CollectionMovie>.toRenderRowItem() = map {
    RenderStateRowItemCollection(
        id = it,
        title = it.name ?: "",
        image = it.cover?.url ?: ""
    )
}

@JvmName("listMovieToRenderItemMovie")
internal fun List<Movie>.toRenderRowItem() = map {
    RenderStateRowItemMovie(
        id = it,
        title = it.name ?: "",
        image = it.poster?.url ?: "",
        rating = it.rating?.kp,
        top250 = it.top250
    )
}