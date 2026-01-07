package com.mordva.movie.domain.model

import com.mordva.domain.model.movie.Movie
import com.mordva.movie.presentation.movie.widget.scoreBottomSheet.ScoreSheetAction

internal data class RatedMovieActionParams(
    val scoreSheetAction: ScoreSheetAction,
    val currentScore: Int,
    val movie: Movie
)