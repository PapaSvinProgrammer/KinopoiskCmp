package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.image.CollectionMovie
import com.mordva.domain.repository.CollectionRepository
import com.mordva.network.external.CollectionService

internal class CollectionRepositoryImpl(
    private val service: CollectionService
) : CollectionRepository {
    override suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionMovie>> {
        return service.getCollectionByFilter(queryParameters).map { it.toDomain() }
    }

    override suspend fun getCollectionBySlug(slug: String): Result<CollectionMovie> {
        return service.getCollectionBySlug(slug).map { it.toDomain() }
    }
}