package com.mordva.movie.presentation.randommovie.widget

import com.mordva.domain.model.image.Poster

internal sealed interface RandomMovieAction {
    data object GoBack : RandomMovieAction
    data object Refresh : RandomMovieAction
    data object MovieLiked : RandomMovieAction
    data object MoviePackageAdded : RandomMovieAction
    data object SearchSimilar : RandomMovieAction

    data class PersonClicked(val person: Int) : RandomMovieAction
    data object ShowAllPersonClicked : RandomMovieAction

    data object ShowAllImagesClicked : RandomMovieAction
    data class ImageClicked(val poster: Poster) : RandomMovieAction
}