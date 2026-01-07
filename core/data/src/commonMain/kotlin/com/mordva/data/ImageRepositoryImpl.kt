package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.data.mapper.toDto
import com.mordva.domain.model.image.MovieImageParams
import com.mordva.domain.model.image.Poster
import com.mordva.domain.repository.ImageRepository
import com.mordva.network.external.ImageService

internal class ImageRepositoryImpl(
    private val service: ImageService
) : ImageRepository {
    override suspend fun getMovieImages(movieId: Int, page: Int): Result<List<Poster>> {
        return service.getMovieImages(movieId, page).map { it.toDomain() }
    }

    override suspend fun getMoviesImagesByType(params: MovieImageParams): Result<List<Poster>> {
        return service.getMoviesImagesByType(params.toDto()).map { it.toDomain() }
    }
}