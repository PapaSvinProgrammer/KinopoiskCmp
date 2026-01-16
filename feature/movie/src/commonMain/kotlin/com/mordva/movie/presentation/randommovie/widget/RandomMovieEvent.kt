package com.mordva.movie.presentation.randommovie.widget

internal sealed interface RandomMovieEvent {
    data object Stub : RandomMovieEvent
}