package com.mordva.movie.presentation.movie_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.domain.model.movie.Movie
import com.mordva.movie.presentation.movie_list.widget.RenderLargeContent
import com.mordva.movie.presentation.movie_list.widget.RenderRowContent
import com.mordva.navigation.MovieScreenType
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieListScreen(
    viewModel: MovieListViewModel,
    type: MovieScreenType,
    onBackClick: () -> Unit,
    onSettingsClick: (Movie) -> Unit,
    onMovieClick: (Movie) -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed by remember {
        derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 }
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    TopBarText(
                        text = uiState.anyState.title,
                        isCollapsed = isCollapsed
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        when (type) {
            MovieScreenType.ROW -> {
                RenderRowContent(
                    state = uiState.listState,
                    modifier = Modifier.padding(innerPadding),
                    onClick = onMovieClick,
                    onSettingsClick = onSettingsClick,
                    onLoadMore = { viewModel.loadMoreItems() }
                )
            }

            MovieScreenType.LARGE -> {
                RenderLargeContent(
                    state = uiState.listState,
                    modifier = Modifier.padding(innerPadding),
                    onClick = onMovieClick,
                    onLoadMore = { viewModel.loadMoreItems() }
                )
            }
        }
    }
}

@Composable
private fun TopBarText(
    text: String,
    isCollapsed: Boolean
) {
    if (isCollapsed) {
        TitleTopBarText(text)
    } else {
        Text(
            text = text,
            fontSize = Typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
    }
}