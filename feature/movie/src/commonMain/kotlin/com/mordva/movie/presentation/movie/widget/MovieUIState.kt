package com.mordva.movie.presentation.movie.widget

import com.mordva.domain.model.PackageType
import com.mordva.domain.model.image.CollectionMovie
import com.mordva.domain.model.image.Poster
import com.mordva.domain.model.local.RatedMovie
import com.mordva.domain.model.movie.Comment
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.movie.widget.scoreBottomSheet.RatedMovieState

internal data class MovieUIState(
    val movieState: MovieState = MovieState.Loading,
    val actors: List<PersonMovie> = listOf(),
    val voiceActors: List<PersonMovie> = listOf(),
    val supportPersonal: List<PersonMovie> = listOf(),
    val comments: List<Comment> = listOf(),
    val images: List<Poster> = listOf(),
    val collections: List<CollectionMovie> = listOf(),
    val ratedMoviesState: RatedMovieState = RatedMovieState.Init,
    val selectedPackage: Set<PackageType> = setOf(),
    val packageSize: Map<PackageType, Int> = mapOf(),
    val isRatedMovieState: RatedMovie? = null,
    val scoreSheetVisible: Boolean = false,
    val moreSheetVisible: Boolean = false,
    val packageSheetVisible: Boolean = false,
    val currentMovieRating: Int = -1,
    val selectedFact: String = "",
    val isWillWatch: Boolean = false,
    val isViewed: Boolean = false,
    val isBlocked: Boolean = false,
)