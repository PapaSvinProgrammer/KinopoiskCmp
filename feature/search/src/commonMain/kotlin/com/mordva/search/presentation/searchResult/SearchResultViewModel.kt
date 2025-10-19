package com.mordva.search.presentation.searchResult

import androidx.lifecycle.ViewModel
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.search.presentation.searchResult.widget.ResultUiState
import com.mordva.ui.uiState.MovieListUIState
import com.mordva.util.Constants
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class SearchResultViewModel(
    private val getMovieByFilter: GetMovieByFilter
) : ViewModel() {
    private val _state = MutableStateFlow(ResultUiState())
    val state = _state.asStateFlow()

    fun getMovies(queryParameters: List<Pair<String, String>>) = launchWithoutOld(GET_MOVIES_JOB) {
        if (state.value.movieState is MovieListUIState.Success) return@launchWithoutOld

        val res = getMovieByFilter.execute(queryParameters)

        res.onSuccess { movies ->
            _state.update {
                it.copy(movieState = MovieListUIState.Success(movies))
            }
        }
    }

    fun loadMoreMovies(
        queryParameters: List<Pair<String, String>>
    ) = launchWithoutOld(GET_MOVIES_JOB) {
        _state.update {
            it.copy(page = it.page + 1)
        }

        val newQuery = mutableListOf<Pair<String, String>>()
        newQuery.addAll(queryParameters)
        newQuery.add(Constants.PAGE_FIELD to state.value.page.toString())

        val res = getMovieByFilter.execute(newQuery)

        res.onSuccess { movies ->
            val newList = (state.value.movieState as MovieListUIState.Success).data.toMutableList()
            newList.addAll(movies)

            _state.update {
                it.copy(movieState = MovieListUIState.Success(newList))
            }
        }
    }

    private companion object {
        const val GET_MOVIES_JOB = "get_movies"
    }
}
