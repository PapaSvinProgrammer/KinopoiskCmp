package com.mordva.network.internal.service

import com.mordva.network.external.CategoryService
import com.mordva.network.external.model.category.ItemNameDto
import com.mordva.network.internal.core.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class CategoryServiceImpl(
    private val client: HttpClient
) : CategoryService {
    override suspend fun getGenres(): Result<List<ItemNameDto>> {
        return safeCall<List<ItemNameDto>> {
            client.get("v1/movie/possible-values-by-field?field=genres.name")
        }
    }

    override suspend fun getMovieTypes(): Result<List<ItemNameDto>> {
        return safeCall<List<ItemNameDto>> {
            client.get("v1/movie/possible-values-by-field?field=type")
        }
    }

    override suspend fun getCountries(): Result<List<ItemNameDto>> {
        return safeCall<List<ItemNameDto>> {
            client.get("v1/movie/possible-values-by-field?field=countries.name")
        }
    }
}