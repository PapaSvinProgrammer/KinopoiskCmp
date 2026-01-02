package com.mordva.network.external

import com.mordva.network.external.model.image.CollectionDto

interface CollectionService {
    suspend fun getCollectionByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionDto>>

    suspend fun getCollectionBySlug(slug: String): Result<CollectionDto>
}