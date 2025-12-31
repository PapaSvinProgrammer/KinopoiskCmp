package com.mordva.movie_list

import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.model.movie.Movie
import com.mordva.movie_list.widget.UiState
import com.mordva.util.Constants
import com.mordva.util.ListViewModel

internal class MovieListViewModel(
    private val title: String,
    private val queryParameters: List<Pair<String, String>>,
    private val getMovieByFilter: GetMovieByFilter
) : ListViewModel<Movie, UiState>(UiState()) {
    init {
        updateAnyState { it.copy(title = title) }
        loadItems()
    }

    override suspend fun getItems(page: Int): Result<List<Movie>> {
        val query = queryParameters.toMutableList()
        query.add(Constants.PAGE_FIELD to page.toString())
        return getMovieByFilter.execute(query)
    }
}
