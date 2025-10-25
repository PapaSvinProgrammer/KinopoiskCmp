package com.mordva.search.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.CollectionListGraph
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.MovieListGraph
import com.mordva.navigation.PersonGraph
import com.mordva.navigation.SearchGraph
import com.mordva.search.presentation.searchResult.SearchResultScreen
import com.mordva.search.presentation.searchResult.SearchResultViewModel
import com.mordva.search.presentation.searchScreen.SearchScreen
import com.mordva.search.presentation.searchScreen.SearchViewModel
import com.mordva.search.presentation.searchScreen.util.navigateToMovieList
import com.mordva.search.presentation.searchSettings.SearchSettingsScreen
import com.mordva.search.presentation.searchSettings.SearchSettingsViewModel
import com.mordva.util.Constants
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

class SearchFeatureImpl : FeatureApi {
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
                    onSettings = { navController.navigate(SearchSettingsRoute) },
                    onItemContent = {
                        if (it.isMovie) {
                            navController.navigate(MovieGraph.MovieRoute(it.id)) {
                                launchSingleTop = true
                            }
                        } else {
                            navController.navigate(PersonGraph.PersonRoute(it.id)) {
                                launchSingleTop = true
                            }
                        }
                    },
                    onNavigateToMovieList = { collection ->
                        navigateToMovieList(navController, collection)
                    },
                    onNavigateToCollectionList = { collection ->
                        navController.navigate(CollectionListGraph.CollectionListRoute(collection))
                    },
                    onMovieClick = { movie ->
                        navController.navigate(MovieGraph.MovieRoute(movie.id))
                    },
                    onCollectionShowAll = {
                        navController.navigate(CollectionListGraph.CollectionListRoute("Фильмы"))
                    },
                    onMovieShowAllClick = { title ->
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
                )
            }

            composable<SearchResultRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val data = it.toRoute<SearchResultRoute>()
                val viewModel: SearchResultViewModel = koinViewModel()

                SearchResultScreen(
                    navController = navController,
                    viewModel = viewModel,
                    queryParameters = data.queryParameters,
                )
            }

            composable<SearchSettingsRoute> {
                val viewModel: SearchSettingsViewModel = koinViewModel()

                SearchSettingsScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}