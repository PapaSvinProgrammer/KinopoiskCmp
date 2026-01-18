package com.mordva.movie.presentation.randommovie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.movie.presentation.randommovie.widget.RandomMovieEvent
import com.mordva.movie.presentation.randommovie.widget.action
import com.mordva.movie.presentation.randommovie.widget.component.RandomMovieBackgroundPager
import com.mordva.movie.presentation.randommovie.widget.component.RandomMoviePager
import com.mordva.movie.presentation.randommovie.widget.component.RandomMovieToolbar
import com.mordva.movie.presentation.randommovie.widget.component.RandomMovieTopBar
import com.mordva.movie.presentation.randommovie.widget.getMoviePosters
import com.mordva.ui.theme.DsSpacer
import com.mordva.util.ObserveAsEvents
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RandomMovieScreen(
    onEvent: (RandomMovieAction) -> Unit,
    viewModel: RandomMovieViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val hazeState = rememberHazeState()
    val moviePagerState = rememberPagerState(pageCount = { state.items.size })
    val backgroundPagerState = rememberPagerState(pageCount = { state.items.size })

    ObserveAsEvents(viewModel.uiEvents) {
        when (it) {
            RandomMovieEvent.Stub -> Unit
        }
    }

    LaunchedEffect(moviePagerState.currentPage, moviePagerState.currentPageOffsetFraction) {
        backgroundPagerState.scrollToPage(
            page = moviePagerState.currentPage,
            pageOffsetFraction = moviePagerState.currentPageOffsetFraction
        )
    }

    Scaffold(
        topBar = {
            RandomMovieTopBar(
                onAction = { },
                hazeState = hazeState
            )
        },
        bottomBar = {
            RandomMovieToolbar(
                onAction = { viewModel.action(it) },
                hazeState = hazeState,
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = DsSpacer.M10)
                    .clip(CircleShape)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState)
        ) {
            RandomMovieBackgroundPager(
                state = backgroundPagerState,
                itemState = state.pagerItemState,
                items = state.getMoviePosters(),
                modifier = Modifier.align(Alignment.TopCenter)
            )

            RandomMoviePager(
                state = moviePagerState,
                itemState = state.pagerItemState,
                items = state.items,
                onItemClick = { viewModel.onPagerItemClicked() },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
