package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.category.ItemName
import com.mordva.domain.repository.CategoryRepository
import com.mordva.network.external.CategoryService

internal class CategoryRepositoryImpl(
    private val service: CategoryService
) : CategoryRepository {
    override suspend fun getGenres(): Result<List<ItemName>> {
        return service.getGenres().map { it.toDomain() }
    }

    override suspend fun getMovieTypes(): Result<List<ItemName>> {
        return service.getMovieTypes().map { it.toDomain() }
    }

    override suspend fun getCounties(): Result<List<ItemName>> {
        return service.getCountries().map { it.toDomain() }
    }
}