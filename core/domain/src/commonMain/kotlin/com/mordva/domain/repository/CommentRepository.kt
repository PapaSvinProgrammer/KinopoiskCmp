package com.mordva.domain.repository

import com.mordva.domain.model.movie.Comment

interface CommentRepository {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>>
}