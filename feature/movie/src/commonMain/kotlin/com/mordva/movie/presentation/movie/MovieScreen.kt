package com.mordva.movie.presentation.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import com.mordva.movie.presentation.movie.widget.collapsingTopBar.BackdropContent
import com.mordva.movie.presentation.movie.widget.collapsingTopBar.CollapsedTopBar
import com.mordva.movie.presentation.movie.widget.collapsingTopBar.ExpandedContent
import com.mordva.movie.presentation.movie.widget.itemContent.collectionsItem
import com.mordva.movie.presentation.movie.widget.itemContent.commentsItem
import com.mordva.movie.presentation.movie.widget.itemContent.factsItem
import com.mordva.movie.presentation.movie.widget.itemContent.imagesItem
import com.mordva.movie.presentation.movie.widget.itemContent.movieDescriptionItem
import com.mordva.movie.presentation.movie.widget.itemContent.personGridHorizontalItem
import com.mordva.movie.presentation.movie.widget.itemContent.premierItem
import com.mordva.movie.presentation.movie.widget.itemContent.ratingCardLargeItem
import com.mordva.movie.presentation.movie.widget.itemContent.seasonDescriptionItem
import com.mordva.movie.presentation.movie.widget.itemContent.sequelsAndPrequelsItem
import com.mordva.movie.presentation.movie.widget.itemContent.similarMoviesItem
import com.mordva.movie.presentation.movie.widget.itemContent.supportPersonalItem
import com.mordva.movie.presentation.movie.widget.itemContent.voiceActorsItem
import com.mordva.movie.presentation.movie.widget.itemContent.watchabilityItem
import com.mordva.movie.presentation.movie.widget.moreBottomSheet.MoreBottomSheet
import com.mordva.movie.presentation.movie.widget.scoreBottomSheet.ScoreBottomSheet
import com.mordva.movie.utils.body
import com.mordva.movie.utils.handleSnackBarSate
import com.mordva.movieScreen.presentation.movie.widget.moreBottomSheet.MoreSheetAction
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.widget.bottomSheets.FactSheet
import com.mordva.ui.widget.component.BasicLoadingBox
import com.mordva.ui.widget.other.TitleTopBarText
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
    onShowAllPersons: (List<PersonMovie>) -> Unit,
    onShowAllCollections: (List<String>) -> Unit,
    onShowAllImages: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    val scrollState = rememberLazyListState()
    val firstOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val index by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }

    LaunchedEffect(firstOffset, index) {
        if (index == 0) {
            viewModel.updateCollapsedState(firstOffset > 800)
        }
    }

    RenderMovieContent(state = state.movieState)

    state.movieState.body().let { movie ->
        Box(modifier = Modifier.fillMaxSize()) {
            BackdropContent(
                scrollState = scrollState,
                movie = movie
            )

            CollapsedTopBar(
                isCollapsed = state.isCollapsed,
                title = { TitleTopBarText(text = movie.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = { }
            )

            LazyColumn(state = scrollState) {
                item {
                    ExpandedContent(
                        movie = movie,
                        isCustomRating = state.isRatedMovieState?.rating,
                        isWillWatchPackage = state.isWillWatch,
                        onEvaluate = {
                            viewModel.updateScoreSheetVisible(true)
                        },
                        onAddIntoFuturePackage = {
                            viewModel.handleWillWatchAction()
                            scope.launch {
                                snackbarState.handleSnackBarSate(state.isWillWatch)
                            }
                        },
                        onShare = {
                            //context.shareMovieIntent(state.movieState.body())
                        },
                        onMore = {
                            viewModel.updateMoreSheetVisible(true)
                        }
                    )
                }

                movieDescriptionItem(movie)

                seasonDescriptionItem(movie)

                watchabilityItem(movie) {
                    onWatchabilityClick(movie.watchability)
                }

                ratingCardLargeItem(
                    movie = movie,
                    onClick = {
                        viewModel.updateScoreSheetVisible(true)
                    }
                )

                personGridHorizontalItem(
                    actors = state.actors,
                    personOnClick = onPersonClick,
                    onClick = { onShowAllPersons(movie.persons) }
                )

                supportPersonalItem(
                    supportPersonal = state.supportPersonal,
                    onClick = onPersonClick,
                    onShowAllPersons = { onShowAllPersons(state.supportPersonal) }
                )

                voiceActorsItem(
                    voiceActors = state.voiceActors,
                    onShowAllPersons = { onShowAllPersons(state.voiceActors) },
                    onClick = onPersonClick
                )

                commentsItem(state.comments)

                imagesItem(
                    images = state.images,
                    onShowAll = onShowAllImages
                )

                collectionsItem(
                    data = state.collections,
                    onClick = onCollectionClick,
                    onShowAll = { onShowAllCollections(movie.lists) }
                )

                factsItem(movie.facts)

                premierItem(movie.premiere)

                sequelsAndPrequelsItem(
                    list = movie.sequelsAndPrequels,
                    onClick = onMovieClick
                )

                similarMoviesItem(
                    similarMovies = movie.similarMovies,
                    onClick = onMovieClick
                )

                item { Spacer(modifier = Modifier.height(130.dp)) }
            }
        }
    }

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

@Composable
private fun RenderMovieContent(
    modifier: Modifier = Modifier,
    state: MovieUIState
) {
    when (state) {
        MovieUIState.Loading -> BasicLoadingBox(modifier)
        is MovieUIState.Success -> {}
    }
}
