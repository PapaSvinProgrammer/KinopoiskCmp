package com.mordva.movie.di

import com.mordva.movie.domain.FilterCollection
import com.mordva.movie.domain.FilterPersonsLikeActors
import com.mordva.movie.domain.FilterPersonsLikeSupport
import com.mordva.movie.domain.FilterPersonsLikeVoiceActors
import com.mordva.movie.domain.GetPersonLittleById
import com.mordva.movie.domain.GroupPersonsByProfession
import com.mordva.movie.domain.HandleBlockedAction
import com.mordva.movie.domain.HandleFavoritePackageAction
import com.mordva.movie.domain.HandleRatedMovieAction
import com.mordva.movie.domain.HandleViewedAction
import com.mordva.movie.domain.HandleWillWatchAction
import com.mordva.movie.domain.UnionPersonsAndPersonMovie
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.presentation.groupPerson.GroupPersonViewModel
import com.mordva.movie.presentation.movie.MovieViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { (movieId: Int) ->
        MovieViewModel(
            movieId = movieId,
            getMovieById = get(),
            getMovieImages = get(),
            getCollectionBySlug = get(),
            getCommentByDate = get(),
            filterCollection = get(),
            filterPersonsLikeVoiceActors = get(),
            filterPersonsLikeActors = get(),
            filterPersonsLikeSupport = get(),
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
        )
    }

    viewModel { (persons: List<PersonMovieScreenObject>) ->
        GroupPersonViewModel(
            persons = persons,
            getPersonByFilter = get(),
            groupPersonsByProfession = get(),
            unionPersonsAndPersonMovie = get(),
        )
    }

    factoryOf(::FilterCollection)
    factoryOf(::FilterPersonsLikeActors)
    factoryOf(::FilterPersonsLikeSupport)
    factoryOf(::FilterPersonsLikeVoiceActors)
    factoryOf(::GetPersonLittleById)
    factoryOf(::GroupPersonsByProfession)
    factoryOf(::UnionPersonsAndPersonMovie)
    factoryOf(::HandleBlockedAction)
    factoryOf(::HandleFavoritePackageAction)
    factoryOf(::HandleRatedMovieAction)
    factoryOf(::HandleViewedAction)
    factoryOf(::HandleWillWatchAction)
}