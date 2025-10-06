package com.mordva.movie.domain

import com.mordva.model.image.CollectionMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers

internal class FilterCollection(
) : UseCase<List<CollectionMovie>, List<CollectionMovie>>(Dispatchers.Default) {
    override suspend fun run(params: List<CollectionMovie>): List<CollectionMovie> {
        return params.filter { it.slug != "hd" }
    }
}
