package com.mordva.search.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.CollectionListGraph
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.MovieListGraph
import com.mordva.navigation.PersonGraph
import com.mordva.navigation.SearchGraph
import com.mordva.search.di.SearchSettingsScope
import com.mordva.search.presentation.search_screen.SearchScreen
import com.mordva.search.presentation.search_screen.SearchViewModel
import com.mordva.search.presentation.search_screen.state.SearchScreenEvent
import com.mordva.search.presentation.search_screen.util.navigateToMovieList
import com.mordva.search.presentation.search_settings.SearchSettingsScreen
import com.mordva.search.presentation.search_settings.SearchSettingsViewModel
import com.mordva.search.presentation.search_settings.widget.SearchSettingsScreenEvent
import com.mordva.search.presentation.search_settings_list.SearchSettingsListScreen
import com.mordva.search.presentation.search_settings_list.SearchSettingsListViewModel
import com.mordva.util.Constants
import com.mordva.util.rememberNavScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named

class SearchFeatureImpl : FeatureApi {
    @OptIn(KoinExperimentalAPI::class)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<SearchGraph>(
            startDestination = SearchRoute
        ) {
            composable<SearchRoute> {
                val viewModel: SearchViewModel = koinViewModel()

                SearchScreen(
                    viewModel = viewModel,
                    eventHandler = { it.navigate(navController) },
                )
            }

            composable<SearchSettingsRoute> { entry ->
                val scope = rememberNavScope(
                    entry = entry,
                    qualifier = named<SearchSettingsScope>()
                )

                val viewModel: SearchSettingsViewModel = koinViewModel(scope = scope)

                SearchSettingsScreen(
                    viewModel = viewModel,
                    eventHandler = { it.navigate(navController) }
                )
            }

            composable<SearchSettingsListRoute> { entry ->
                val parentEntry = remember(entry) {
                    navController.getBackStackEntry<SearchSettingsRoute>()
                }

                val scope = rememberNavScope(
                    entry = parentEntry,
                    qualifier = named<SearchSettingsScope>()
                )

                val viewModel: SearchSettingsListViewModel = koinViewModel(scope = scope)

                SearchSettingsListScreen(
                    type = entry.toRoute<SearchSettingsListRoute>().type,
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

private fun SearchScreenEvent.navigate(navController: NavHostController) = when (this) {
    is SearchScreenEvent.ShowAllMovies -> {
        val queryParams = listOf(
            Constants.IS_SERIES_FIELD to Constants.TRUE,
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        navController.navigate(
            MovieListGraph.MovieListRoute(
                title = title,
                queryParameters = queryParams
            )
        )
    }

    SearchScreenEvent.ShowCollectionAll -> {
        navController.navigate(CollectionListGraph.CollectionListRoute("Фильмы"))
    }

    is SearchScreenEvent.ShowCollectionList -> {
        navController.navigate(CollectionListGraph.CollectionListRoute(collection))
    }

    is SearchScreenEvent.ShowItemContent -> {
        if (item.isMovie) {
            navController.navigate(MovieGraph.MovieRoute(item.id)) {
                launchSingleTop = true
            }
        } else {
            navController.navigate(PersonGraph.PersonRoute(item.id)) {
                launchSingleTop = true
            }
        }
    }

    is SearchScreenEvent.ShowMovie -> navController.navigate(MovieGraph.MovieRoute(movie.id))
    is SearchScreenEvent.ShowMovieList -> navigateToMovieList(navController, collection)
    SearchScreenEvent.ShowSettings -> navController.navigate(SearchSettingsRoute)
}

private fun SearchSettingsScreenEvent.navigate(navController: NavController) = when (this) {
    SearchSettingsScreenEvent.GoBack -> navController.popBackStack()
    is SearchSettingsScreenEvent.ShowSearchSettingsList -> {
        navController.navigate(SearchSettingsListRoute(type))
    }
}