package com.mordva.movie.presentation.randommovie.widget

import com.mordva.domain.model.image.Poster
import com.mordva.movie.presentation.randommovie.RandomMovieViewModel

internal sealed interface RandomMovieAction {
    data object RefreshDragged : RandomMovieAction
    data object SearchSimilar : RandomMovieAction

    data class PersonClicked(val person: Int) : RandomMovieAction
    data object ShowAllPersonClicked : RandomMovieAction

    data object ShowAllImagesClicked : RandomMovieAction
    data class ImageClicked(val poster: Poster) : RandomMovieAction

    sealed interface TopBarAction : RandomMovieAction {
        data object GoBackClicked : TopBarAction
    }

    sealed interface BottomBarAction : RandomMovieAction {
        data object SearchClamped : BottomBarAction
        data object SearchClicked : BottomBarAction
        data object MovieLiked : BottomBarAction
        data object MoviePackageAdded : BottomBarAction
    }
}

internal fun RandomMovieViewModel.action(action: RandomMovieAction) = when (action) {
    is RandomMovieAction.ImageClicked -> Unit
    is RandomMovieAction.PersonClicked -> Unit
    RandomMovieAction.RefreshDragged -> Unit
    RandomMovieAction.SearchSimilar -> Unit
    RandomMovieAction.ShowAllImagesClicked -> Unit
    RandomMovieAction.ShowAllPersonClicked -> Unit
    RandomMovieAction.BottomBarAction.MovieLiked -> Unit
    RandomMovieAction.BottomBarAction.MoviePackageAdded -> Unit
    RandomMovieAction.BottomBarAction.SearchClamped -> onSearchClamped()
    RandomMovieAction.BottomBarAction.SearchClicked -> Unit
    RandomMovieAction.TopBarAction.GoBackClicked -> Unit
}