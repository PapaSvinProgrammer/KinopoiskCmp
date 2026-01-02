package com.mordva.network.internal.service

import com.mordva.network.external.ImageService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.external.model.Docs
import com.mordva.network.external.model.image.MovieImageParamsDto
import com.mordva.network.external.model.image.PosterDto
import com.mordva.network.internal.util.toKtorString
import com.mordva.util.Constants.LIMIT_FIELD
import com.mordva.util.Constants.MOVIE_ID_FIELD
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.TYPE_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class ImageServiceImpl(
    private val client: HttpClient
) : ImageService {
    override suspend fun getMovieImages(movieId: Int, page: Int): Result<List<PosterDto>> {
        return safeCall<Docs<PosterDto>> {
            client.get("v1.4/image") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, page.toString())
                    parameters.append(MOVIE_ID_FIELD, movieId.toString())
                }
            }
        }.map { doc -> doc.docs }
    }

    override suspend fun getMoviesImagesByType(params: MovieImageParamsDto): Result<List<PosterDto>> {
        return safeCall<Docs<PosterDto>> {
            client.get("v1.4/image") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, params.page.toString())
                    parameters.append(MOVIE_ID_FIELD, params.movieId.toString())
                    params.types.forEach { parameters.append(TYPE_FIELD, it.toKtorString()) }
                }
            }
        }.map { doc -> doc.docs }
    }
}