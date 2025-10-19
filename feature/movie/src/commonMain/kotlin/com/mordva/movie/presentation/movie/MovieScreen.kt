package com.mordva.movie.presentation.movie

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.model.image.CollectionMovie
import com.mordva.model.movie.Movie
import com.mordva.model.movie.Watchability
import com.mordva.model.person.PersonMovie
import com.mordva.movie.presentation.movie.widget.RenderMovieContent
import com.mordva.movie.presentation.movie.widget.moreBottomSheet.MoreBottomSheet
import com.mordva.movie.presentation.movie.widget.scoreBottomSheet.ScoreBottomSheet
import com.mordva.movie.utils.body
import com.mordva.movie.utils.handleSnackBarSate
import com.mordva.movie.utils.rememberCollapsedState
import com.mordva.movieScreen.presentation.movie.widget.moreBottomSheet.MoreSheetAction
import com.mordva.ui.widget.bottomSheets.FactSheet
import com.mordva.ui.widget.packageBottomSheet.PackageBottomSheet
import kotlinx.coroutines.launch

@Composable
internal fun MovieScreen(
    viewModel: MovieViewModel,
    onBackClick: () -> Unit,
    onPersonClick: (PersonMovie) -> Unit,
    onWatchabilityClick: (Watchability) -> Unit,
    onCollectionClick: (CollectionMovie) -> Unit,
    onMovieClick: (Movie) -> Unit,
    onShowAllPersonsClick: (List<PersonMovie>) -> Unit,
    onShowAllCollectionsClick: (List<String>) -> Unit,
    onShowAllImagesClick: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    val scrollState = rememberLazyListState()
    val isCollapsed by rememberCollapsedState(scrollState)

    RenderMovieContent(
        uiState = state,
        scrollState = scrollState,
        isCollapsed = isCollapsed,
        onBackClick = onBackClick,
        onPersonClick = onPersonClick,
        onWatchabilityClick = onWatchabilityClick,
        onCollectionClick = onCollectionClick,
        onMovieClick = onMovieClick,
        onShowAllPersonsClick = onShowAllPersonsClick,
        onShowAllCollectionsClick = onShowAllCollectionsClick,
        onShowAllImagesClick = onShowAllImagesClick,
        onEvaluateClick = { viewModel.updateMoreSheetVisible(true) },
        onPackageClick = {
            viewModel.handleWillWatchAction()
            scope.launch {
                snackbarState.handleSnackBarSate(state.isWillWatch)
            }
        },
        onMoreClick = { viewModel.updateMoreSheetVisible(true) },
        onShareClick = {  },
    )

    if (state.selectedFact.isNotEmpty()) {
        FactSheet(
            text = state.selectedFact,
            onDismissRequest = {
                viewModel.updateSelectedFact("")
            }
        )
    }

    if (state.scoreSheetVisible) {
        ScoreBottomSheet(
            movie = state.movieState.body(),
            initialValue = state.isRatedMovieState?.rating,
            ratedMovieState = state.ratedMoviesState,
            onAction = { action ->
                viewModel.handleScoreSheetHandle(action)
            },
            onDismissRequest = {
                viewModel.updateScoreSheetVisible(false)
            },
            onValueChange = {
                if (it == state.currentMovieRating) return@ScoreBottomSheet

                viewModel.updateCurrentRatingMovie(it)
                viewModel.getRatedMovies(it)
            }
        )
    }

    if (state.moreSheetVisible) {
        MoreBottomSheet(
            movie = state.movieState.body(),
            isBlocked = state.isBlocked,
            isViewed = state.isViewed,
            onAction = { action ->
                when (action) {
                    MoreSheetAction.AddInFolder -> viewModel.updatePackageVisible(true)
                    MoreSheetAction.BlockedChange -> viewModel.handleBlockedAction()
                    MoreSheetAction.VisibilityChange -> viewModel.handleViewedAction()
                }
            },
            onDismissRequest = {
                viewModel.updateMoreSheetVisible(false)
            }
        )
    }

    if (state.packageSheetVisible) {
        PackageBottomSheet(
            movie = state.movieState.body(),
            selectedSet = state.selectedPackage,
            packageSize = state.packageSize,
            onAction = { action ->
                viewModel.handlePackageAction(action)
            },
            onDismissRequest = {
                viewModel.updatePackageVisible(false)
            }
        )
    }

    SnackbarHost(
        modifier = Modifier.safeContentPadding(),
        hostState = snackbarState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.fillMaxWidth(),
                snackbarData = data,
                shape = RoundedCornerShape(10.dp)
            )
        }
    )
}
