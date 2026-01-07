package com.mordva.movie.presentation.movie.widget.scoreBottomSheet

import com.mordva.domain.model.local.RatedMovie

internal sealed interface RatedMovieState {
    data object Init : RatedMovieState
    data class Success(val data: List<RatedMovie>) : RatedMovieState
    data object Loading : RatedMovieState
    data class Error(val error: Throwable) : RatedMovieState
}