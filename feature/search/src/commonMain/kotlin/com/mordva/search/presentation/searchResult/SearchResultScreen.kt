package com.mordva.search.presentation.searchResult

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.navigation.MovieGraph
import com.mordva.search.presentation.widget.RenderMovieState
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchResultScreen(
    navController: NavController,
    viewModel: SearchResultViewModel,
    queryParameters: List<Pair<String, String>>
) {
    val movieState by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(Resources.Strings.SearchResult))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LifecycleEventEffect(Lifecycle.Event.ON_START) {
            viewModel.getMovies(queryParameters)
        }

        RenderMovieState(
            state = movieState.movieState,
            modifier = Modifier.padding(innerPadding),
            onClick = {
                navController.navigate(MovieGraph.MovieRoute(it.id)) {
                    launchSingleTop = true
                }
            },
            loadMore = { viewModel.loadMoreMovies(queryParameters) }
        )
    }
}