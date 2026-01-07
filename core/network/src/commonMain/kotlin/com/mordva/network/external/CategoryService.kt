package com.mordva.network.external

import com.mordva.network.external.model.category.ItemNameDto

interface CategoryService {
    suspend fun getGenres(): Result<List<ItemNameDto>>
    suspend fun getMovieTypes(): Result<List<ItemNameDto>>
    suspend fun getCountries(): Result<List<ItemNameDto>>
}