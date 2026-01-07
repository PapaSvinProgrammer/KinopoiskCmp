package com.mordva.search.domain

import com.mordva.domain.model.SearchItem
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.search.domain.model.RequestParams
import com.mordva.search.util.toSearchItemList
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal class SearchByName(
    private val movieRepository: MovieRepository,
    private val personRepository: PersonRepository
) : UseCase<RequestParams, Result<List<SearchItem>>>(Dispatchers.IO) {
    override suspend fun run(params: RequestParams): Result<List<SearchItem>> {
        return when (params.selectedIndex) {
            0 -> getMovieByName(params.q)
            1 -> getPersonByName(params.q)
            else -> Result.failure(Exception())
        }
    }

    private suspend fun getMovieByName(q: String): Result<List<SearchItem>> {
        return movieRepository.search(q).map { it.toSearchItemList() }
    }

    private suspend fun getPersonByName(q: String): Result<List<SearchItem>> {
        return personRepository.searchPersonByName(q).map { it.toSearchItemList() }
    }
}
