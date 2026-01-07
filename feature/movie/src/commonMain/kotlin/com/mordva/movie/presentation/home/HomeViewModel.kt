package com.mordva.movie.presentation.home

import androidx.lifecycle.ViewModel
import com.mordva.domain.usecase.collection.GetCollectionAll
import com.mordva.movie.domain.model.MovieParams
import com.mordva.movie.domain.movie.GetMoviesByCollection
import com.mordva.movie.domain.movie.GetMoviesByCompany
import com.mordva.movie.domain.movie.GetMoviesByGenre
import com.mordva.movie.presentation.home.widget.HomeUIState
import com.mordva.movie.presentation.home.utils.NavigationUtils
import com.mordva.movie.presentation.movie_list.widget.MovieListState
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class HomeViewModel(
    private val getMoviesByGenre: GetMoviesByGenre,
    private val getMoviesByCollection: GetMoviesByCollection,
    private val getCollectionAll: GetCollectionAll,
    private val getMoviesByCompany: GetMoviesByCompany
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    fun getMoviesDrama() = launchWithoutOld(GET_DRAMA_JOB) {
        val params = MovieParams(genre = NavigationUtils.DRAMA_GENRE)
        val res = getMoviesByGenre.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieDramaState = MovieListState.Success(movies))
            }
        }
    }

    fun getMoviesBoevik() = launchWithoutOld(GET_BOEVIK_JOB) {
        val params = MovieParams(genre = NavigationUtils.BOEVIK_GENRE)
        val res = getMoviesByGenre.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBoevikState = MovieListState.Success(movies))
            }
        }.onFailure { error ->
        }
    }

    fun getMoviesBest250() = launchWithoutOld(GET_BEST250_JOB) {
        val params = MovieParams(name = NavigationUtils.LIST250)
        val res = getMoviesByCollection.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBest250State = MovieListState.Success(movies))
            }
        }
    }

    fun getMoviesBest501() = launchWithoutOld(GET_BEST501_JOB) {
        val params = MovieParams(name = NavigationUtils.LIST501)
        val res = getMoviesByCollection.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBest501State = MovieListState.Success(movies))
            }
        }
    }

    fun getMoviesBest100() = launchWithoutOld(GET_BEST100_JOB) {
        val params = MovieParams(name = NavigationUtils.LIST_SCIFI)
        val res = getMoviesByCollection.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieBest100State = MovieListState.Success(movies))
            }
        }
    }

    fun getMoviesHBO() = launchWithoutOld(GET_HBO_JOB) {
        val params = MovieParams(name = NavigationUtils.HBO)
        val res = getMoviesByCompany.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieHBOState = MovieListState.Success(movies))
            }
        }
    }

    fun getMoviesNetflix() = launchWithoutOld(GET_NETFLIX_JOB) {
        val params = MovieParams(name = NavigationUtils.NETFLIX)
        val res = getMoviesByCompany.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(movieNetflixState = MovieListState.Success(movies))
            }
        }
    }

    fun getCollections() = launchWithoutOld(GET_COLLECTIONS_JOB) {
//        getCollectionAll.execute(1).onSuccess { collections ->
//            _uiState.update {
//                it.copy(collectionState = CollectionListState.Success(collections))
//            }
//        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_DRAMA_JOB = "get_movies_drama"
        const val GET_BOEVIK_JOB = "get_movies_boevik"
        const val GET_BEST250_JOB = "get_movies_best250"
        const val GET_BEST501_JOB = "get_movies_best501"
        const val GET_BEST100_JOB = "get_movies_best100"
        const val GET_HBO_JOB = "get_movies_hbo"
        const val GET_NETFLIX_JOB = "get_movies_netflix"
        const val GET_COLLECTIONS_JOB = "get_collections"
    }
}
