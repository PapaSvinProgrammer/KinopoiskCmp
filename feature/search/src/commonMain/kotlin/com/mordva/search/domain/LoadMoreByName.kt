package com.mordva.search.domain

import com.mordva.domain.model.SearchItem
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.search.domain.model.RequestParams
import com.mordva.search.presentation.search_screen.util.toSearchItemList
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal class LoadMoreByName(
    private val movieRepository: MovieRepository,
    private val personRepository: PersonRepository
) : UseCase<RequestParams, Result<List<SearchItem>>>(Dispatchers.IO) {
    override suspend fun run(params: RequestParams): Result<List<SearchItem>> {
        return when (params.selectedIndex) {
            0 -> loadMoreMovieByName(params.q, params.page)
            1 -> loadMorePersonByName(params.q, params.page)
            else -> Result.failure(Exception())
        }
    }

    private suspend fun loadMoreMovieByName(q: String, page: Int): Result<List<SearchItem>> {
        return movieRepository.search(q, page).map { it.toSearchItemList() }
    }

    private suspend fun loadMorePersonByName(q: String, page: Int): Result<List<SearchItem>> {
        return personRepository.searchPersonByName(q, page).map { it.toSearchItemList() }
    }
}
