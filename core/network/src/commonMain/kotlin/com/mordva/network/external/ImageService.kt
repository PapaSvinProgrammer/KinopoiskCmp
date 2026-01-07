package com.mordva.network.external

import com.mordva.network.external.model.image.MovieImageParamsDto
import com.mordva.network.external.model.image.PosterDto

interface ImageService {
    suspend fun getMovieImages(movieId: Int, page: Int = 1): Result<List<PosterDto>>
    suspend fun getMoviesImagesByType(params: MovieImageParamsDto): Result<List<PosterDto>>
}