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
import com.mordva.movie.presentation.groupPerson.GroupPersonViewModel
import com.mordva.movie.presentation.movie.MovieViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieModule = module {
    viewModelOf(::MovieViewModel)
    viewModelOf(::GroupPersonViewModel)
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