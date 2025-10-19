package com.mordva.movie_list.di

import com.mordva.movie_list.MovieListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val movieListModule = module {
    viewModel { (title: String, query: List<Pair<String, String>>) ->
        MovieListViewModel(
            title = title,
            queryParameters = query,
            getMovieByFilter = get(),
        )
    }
}
