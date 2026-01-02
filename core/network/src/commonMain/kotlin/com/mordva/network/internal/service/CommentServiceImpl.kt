package com.mordva.network.internal.service

import com.mordva.network.external.CommentService
import com.mordva.network.external.model.Docs
import com.mordva.network.external.model.movie.CommentDto
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.util.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class CommentServiceImpl(
    private val client: HttpClient
) : CommentService {
    override suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CommentDto>> {
        return safeCall<Docs<CommentDto>> {
            client.get("v1.4/review") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { it.docs }
    }
}