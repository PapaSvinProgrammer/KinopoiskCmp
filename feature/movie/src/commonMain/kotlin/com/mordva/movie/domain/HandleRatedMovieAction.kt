package com.mordva.movie.domain

import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.movie.utils.toRatedMovie
import com.mordva.movie.domain.model.RatedMovieActionParams
import com.mordva.movie.domain.model.RatedMovieParams
import com.mordva.movie.presentation.movie.widget.scoreBottomSheet.ScoreSheetAction
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal class HandleRatedMovieAction(
    private val ratedMovieRepository: RatedMovieRepository
) : UseCase<RatedMovieActionParams, Unit>(Dispatchers.IO) {
    override suspend fun run(params: RatedMovieActionParams) {
        when (params.scoreSheetAction) {
            ScoreSheetAction.Delete -> {
                ratedMovieRepository.delete(params.movie.id)
            }

            is ScoreSheetAction.Save -> {
                val params = RatedMovieParams(
                    movie = params.movie,
                    rating = params.scoreSheetAction.rating
                )

                val ratedMovie = params.movie.toRatedMovie(params.rating)
                ratedMovieRepository.add(ratedMovie)
            }

            ScoreSheetAction.Nothing -> {}
        }
    }
}