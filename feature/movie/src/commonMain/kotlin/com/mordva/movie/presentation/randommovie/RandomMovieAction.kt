package com.mordva.movie.presentation.randommovie

internal sealed interface RandomMovieAction {
    data object GoBack : RandomMovieAction
    data object Refresh : RandomMovieAction
}