package com.mordva.domain.usecase.comment

import com.mordva.domain.repository.CommentRepository
import com.mordva.domain.usecase.comment.model.CommentParams
import com.mordva.model.movie.Comment
import com.mordva.util.UseCase
import com.mordva.util.Constants.MOVIE_ID_FIELD
import com.mordva.util.Constants.NEUTRAL_VALUE
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.TYPE_FIELD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class GetCommentOnlyNeutral(
    private val commentRepository: CommentRepository
) : UseCase<CommentParams, Result<List<Comment>>>(Dispatchers.IO) {
    override suspend fun run(params: CommentParams): Result<List<Comment>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to params.movieId.toString(),
            PAGE_FIELD to params.page.toString(),
            TYPE_FIELD to NEUTRAL_VALUE
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}