package com.mordva.movie.presentation.randommovie.widget

internal sealed interface RandomMovieAction {
    data object GoBack : RandomMovieAction
    data object Refresh : RandomMovieAction
    data object MovieLiked : RandomMovieAction
    data object MoviePackageAdded : RandomMovieAction
    data object SearchSimilar : RandomMovieAction
    data class PersonClicked(val person: Int) : RandomMovieAction
    data object ShowAllPersonCLicked : RandomMovieAction
}