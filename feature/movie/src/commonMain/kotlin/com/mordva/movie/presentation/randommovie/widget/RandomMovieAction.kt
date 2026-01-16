package com.mordva.movie.presentation.randommovie.widget

import com.mordva.domain.model.image.Poster
import com.mordva.movie.presentation.randommovie.RandomMovieViewModel

internal sealed interface RandomMovieAction {
    data object GoBackClicked : RandomMovieAction
    data object RefreshDragged : RandomMovieAction
    data object MovieLiked : RandomMovieAction
    data object MoviePackageAdded : RandomMovieAction
    data object SearchSimilar : RandomMovieAction

    data class PersonClicked(val person: Int) : RandomMovieAction
    data object ShowAllPersonClicked : RandomMovieAction

    data object ShowAllImagesClicked : RandomMovieAction
    data class ImageClicked(val poster: Poster) : RandomMovieAction
}

internal fun RandomMovieViewModel.action(action: RandomMovieAction) = when (action) {
    RandomMovieAction.GoBackClicked -> sendEvent(RandomMovieEvent.Stub)
    is RandomMovieAction.ImageClicked -> Unit
    RandomMovieAction.MovieLiked -> Unit
    RandomMovieAction.MoviePackageAdded -> Unit
    is RandomMovieAction.PersonClicked -> Unit
    RandomMovieAction.RefreshDragged -> Unit
    RandomMovieAction.SearchSimilar -> Unit
    RandomMovieAction.ShowAllImagesClicked -> Unit
    RandomMovieAction.ShowAllPersonClicked -> Unit
}