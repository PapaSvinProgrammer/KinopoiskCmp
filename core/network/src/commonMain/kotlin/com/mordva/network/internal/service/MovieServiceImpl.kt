package com.mordva.network.internal.service

import com.mordva.network.external.MovieService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.external.model.Docs
import com.mordva.network.external.model.movie.MovieDto
import com.mordva.util.Constants.LIMIT_FIELD
import com.mordva.util.Constants.NAME_FIELD
import com.mordva.util.Constants.NOT_NULL_FIELD
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.QUERY_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class MovieServiceImpl(
    private val client: HttpClient
) : MovieService {
    override suspend fun getMovieById(movieId: Int): Result<MovieDto> {
        return safeCall<MovieDto> {
            client.get("v1.4/movie/$movieId")
        }
    }

    override suspend fun searchMovieByName(page: Int, q: String): Result<List<MovieDto>> {
        return safeCall<Docs<MovieDto>> {
            client.get("v1.4/movie/search") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, page.toString())
                    parameters.append(QUERY_FIELD, q)
                }
            }
        }.map { doc -> doc.docs }
    }

    override suspend fun getMoviesByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<MovieDto>> {
        return safeCall<Docs<MovieDto>> {
            client.get("v1.4/movie") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(NOT_NULL_FIELD, NAME_FIELD)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc -> doc.docs }
    }
}