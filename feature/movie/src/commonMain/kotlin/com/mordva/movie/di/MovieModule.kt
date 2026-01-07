package com.mordva.movie.di

import com.mordva.movie.domain.GetPersonOptimizedById
import com.mordva.movie.domain.comment.GetCommentByDate
import com.mordva.movie.domain.comment.GetCommentByType
import com.mordva.movie.domain.comment.GetCommentOnlyNegative
import com.mordva.movie.domain.comment.GetCommentOnlyNeutral
import com.mordva.movie.domain.comment.GetCommentOnlyPositive
import com.mordva.movie.domain.handle.HandleBlockedAction
import com.mordva.movie.domain.handle.HandleFavoritePackageAction
import com.mordva.movie.domain.handle.HandleRatedMovieAction
import com.mordva.movie.domain.handle.HandleViewedAction
import com.mordva.movie.domain.handle.HandleWillWatchAction
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.presentation.groupPerson.GroupPersonViewModel
import com.mordva.movie.domain.movie.GetMoviesByCollection
import com.mordva.movie.domain.movie.GetMoviesByCompany
import com.mordva.movie.domain.movie.GetMoviesByGenre
import com.mordva.movie.domain.studio.GetStudiesByMovieId
import com.mordva.movie.presentation.home.HomeViewModel
import com.mordva.movie.presentation.movie.MovieViewModel
import com.mordva.movie.presentation.movie_list.MovieListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieModule = module {
    viewModel { (movieId: Int) ->
        MovieViewModel(
            movieId = movieId,
            getCommentByDate = get(),
            ratedMovieRepository = get(),
            willWatchPackageRepository = get(),
            handleWillWatchAction = get(),
            handleRatedMovieAction = get(),
            handleFavoritePackageAction = get(),
            handleViewedAction = get(),
            handleBlockedAction = get(),
            favoritePackageRepository = get(),
            viewedRepository = get(),
            blockedRepository = get(),
            movieRepository = get(),
            imageRepository = get(),
            collectionRepository = get(),
        )
    }

    viewModel { (persons: List<PersonMovieScreenObject>) ->
        GroupPersonViewModel(
            persons = persons,
            getPersonByFilter = get(),
        )
    }

    viewModel { (title: String, query: List<Pair<String, String>>) ->
        MovieListViewModel(
            title = title,
            queryParameters = query,
            movieRepository = get(),
        )
    }

    viewModelOf(::HomeViewModel)

    factoryOf(::GetMoviesByCollection)
    factoryOf(::GetMoviesByCompany)
    factoryOf(::GetMoviesByGenre)
    factoryOf(::GetPersonOptimizedById)
    factoryOf(::HandleBlockedAction)
    factoryOf(::HandleFavoritePackageAction)
    factoryOf(::HandleRatedMovieAction)
    factoryOf(::HandleViewedAction)
    factoryOf(::HandleWillWatchAction)
    factoryOf(::GetCommentByDate)
    factoryOf(::GetCommentByType)
    factoryOf(::GetCommentOnlyNegative)
    factoryOf(::GetCommentOnlyNeutral)
    factoryOf(::GetCommentOnlyPositive)
    factoryOf(::GetStudiesByMovieId)
    factoryOf(::GetStudiesByMovieId)
}