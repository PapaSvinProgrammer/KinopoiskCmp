package com.mordva.movie.presentation.home.widget

import com.mordva.movie.presentation.movie_list.widget.MovieListState

internal data class HomeUIState(
    val movieDramaState: MovieListState = MovieListState.Loading,
    val movieBoevikState: MovieListState = MovieListState.Loading,
    val movieBest250State: MovieListState = MovieListState.Loading,
    val movieBest501State: MovieListState = MovieListState.Loading,
    val movieBest100State: MovieListState = MovieListState.Loading,
    val movieHBOState: MovieListState = MovieListState.Loading,
    val movieNetflixState: MovieListState = MovieListState.Loading,
//    val collectionState: CollectionListUIState = CollectionListUIState.Loading,
)