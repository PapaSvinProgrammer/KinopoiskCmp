package com.mordva.kinopoiskkmp.di

import com.mordva.domain.usecase.awards.GetMovieAwardsByDate
import com.mordva.domain.usecase.awards.GetMovieAwardsByTitle
import com.mordva.domain.usecase.awards.GetPersonAwardsByDate
import com.mordva.domain.usecase.awards.GetPersonAwardsByTitle
import com.mordva.domain.usecase.collection.GetCollectionAll
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import com.mordva.domain.usecase.collection.GetCollectionBySlug
import com.mordva.domain.usecase.comment.GetCommentByDate
import com.mordva.domain.usecase.comment.GetCommentByType
import com.mordva.domain.usecase.comment.GetCommentOnlyNegative
import com.mordva.domain.usecase.comment.GetCommentOnlyNeutral
import com.mordva.domain.usecase.comment.GetCommentOnlyPositive
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.domain.usecase.movie.GetMovieById
import com.mordva.domain.usecase.movie.GetMovieImages
import com.mordva.domain.usecase.movie.SearchMovie
import com.mordva.domain.usecase.person.GetPersonByFilter
import com.mordva.domain.usecase.person.GetPersonById
import com.mordva.domain.usecase.person.SearchPerson
import com.mordva.domain.usecase.studio.GetStudiesByMovieId
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::GetMovieAwardsByDate)
    factoryOf(::GetMovieAwardsByTitle)
    factoryOf(::GetPersonAwardsByDate)
    factoryOf(::GetPersonAwardsByTitle)
    factoryOf(::GetCollectionAll)
    factoryOf(::GetCollectionByCategory)
    factoryOf(::GetCollectionBySlug)
    factoryOf(::GetCommentByDate)
    factoryOf(::GetCommentByType)
    factoryOf(::GetCommentOnlyNegative)
    factoryOf(::GetCommentOnlyNeutral)
    factoryOf(::GetCommentOnlyPositive)
    factoryOf(::GetMovieByFilter)
    factoryOf(::GetMovieById)
    factoryOf(::GetMovieImages)
    factoryOf(::SearchMovie)
    factoryOf(::GetPersonByFilter)
    factoryOf(::GetPersonById)
    factoryOf(::SearchPerson)
    factoryOf(::GetStudiesByMovieId)
    factoryOf(::GetStudiesByMovieId)
}