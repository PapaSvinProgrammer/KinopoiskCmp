package com.mordva.network.internal.service

import com.mordva.network.external.CollectionService
import com.mordva.network.external.model.Docs
import com.mordva.network.external.model.image.CollectionDto
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.util.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class CollectionServiceImpl(
    private val client: HttpClient
) : CollectionService {
    override suspend fun getCollectionByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionDto>> {
        return safeCall<Docs<CollectionDto>> {
            client.get("v1.4/list") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { it.docs }
    }

    override suspend fun getCollectionBySlug(slug: String): Result<CollectionDto> {
        return safeCall<CollectionDto> {
            client.get("v1.4/list/$slug")
        }
    }
}