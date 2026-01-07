package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.movie.Comment
import com.mordva.domain.repository.CommentRepository
import com.mordva.network.external.CommentService

internal class CommentRepositoryImpl(
    private val service: CommentService
) : CommentRepository {
    override suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>> {
        return service.getCommentsByFilter(queryParameters).map { it.toDomain() }
    }
}