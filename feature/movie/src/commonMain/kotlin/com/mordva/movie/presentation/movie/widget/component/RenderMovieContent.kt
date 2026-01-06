package com.mordva.movie.presentation.movie.widget.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.image.CollectionMovie
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.movie.Watchability
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.movie.widget.MovieState
import com.mordva.movie.presentation.movie.widget.MovieUIState
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
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.widget.component.BasicLoadingBox
import com.mordva.ui.widget.other.TitleTopBarText

@Composable
internal fun RenderMovieContent(
    uiState: MovieUIState,
    isCollapsed: Boolean,
    onBackClick: () -> Unit,
    onPersonClick: (PersonMovie) -> Unit,
    onWatchabilityClick: (Watchability) -> Unit,
    onCollectionClick: (CollectionMovie) -> Unit,
    onMovieClick: (Movie) -> Unit,
    onShowAllPersonsClick: (List<PersonMovie>) -> Unit,
    onShowAllCollectionsClick: (List<String>) -> Unit,
    onShowAllImagesClick: () -> Unit,
    onEvaluateClick: () -> Unit,
    onPackageClick: () -> Unit,
    onMoreClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
) {
    when (val state = uiState.movieState) {
        MovieState.Loading -> BasicLoadingBox(modifier)
        MovieState.Error -> {}

        is MovieState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                BackdropContent(
                    scrollState = scrollState,
                    movie = state.data,
                )

                CollapsedTopBar(
                    isCollapsed = isCollapsed,
                    title = { TitleTopBarText(text = state.data.name ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = PlatformResources.PlatformIcons.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = { }
                )

                LazyColumn(state = scrollState) {
                    item(key = 1) {
                        ExpandedContent(
                            movie = state.data,
                            isCustomRating = uiState.isRatedMovieState?.rating,
                            isWillWatchPackage = uiState.isWillWatch,
                            onEvaluate = onEvaluateClick,
                            onAddIntoFuturePackage = onPackageClick,
                            onShare = onShareClick,
                            onMore = onMoreClick
                        )
                    }

                    movieDescriptionItem(state.data)

                    seasonDescriptionItem(state.data)

                    watchabilityItem(
                        items = state.data.watchability.items,
                        onWatchabilityClick = { onWatchabilityClick(state.data.watchability) }
                    )

                    ratingCardLargeItem(
                        movie = state.data,
                        onClick = onEvaluateClick
                    )

                    personGridHorizontalItem(
                        actors = uiState.actors,
                        personOnClick = onPersonClick,
                        onClick = { onShowAllPersonsClick(state.data.persons) }
                    )

                    supportPersonalItem(
                        supportPersonal = uiState.supportPersonal,
                        onClick = onPersonClick,
                        onShowAllPersons = { onShowAllPersonsClick(uiState.supportPersonal) }
                    )

                    voiceActorsItem(
                        voiceActors = uiState.voiceActors,
                        onShowAllPersons = { onShowAllPersonsClick(uiState.voiceActors) },
                        onClick = onPersonClick
                    )

                    commentsItem(uiState.comments)

                    imagesItem(
                        images = uiState.images,
                        onShowAll = onShowAllImagesClick
                    )

                    collectionsItem(
                        data = uiState.collections,
                        onClick = onCollectionClick,
                        onShowAll = { onShowAllCollectionsClick(state.data.lists) }
                    )

                    factsItem(state.data.facts)

                    premierItem(state.data.premiere)

                    sequelsAndPrequelsItem(
                        list = state.data.sequelsAndPrequels,
                        onClick = onMovieClick
                    )

                    similarMoviesItem(
                        similarMovies = state.data.similarMovies,
                        onClick = onMovieClick
                    )

                    item { Spacer(modifier = Modifier.height(130.dp)) }
                }
            }
        }
    }
}
