package com.mordva.network.external

import com.mordva.network.external.model.movie.CommentDto

interface CommentService {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CommentDto>>
}