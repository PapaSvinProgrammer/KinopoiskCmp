package com.mordva.movie_list.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.movie_list.MovieListScreen
import com.mordva.movie_list.MovieListViewModel
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.MovieListGraph
import com.mordva.navigation.MovieScreenType
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

class MovieListFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<MovieListGraph>(
            startDestination = MovieListGraph.MovieListRoute("", listOf())
        ) {
            composable<MovieListGraph.MovieListRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair,
                    typeOf<MovieScreenType>() to CustomNavType.MovieScreenType,
                )
            ) {
                val route = it.toRoute<MovieListGraph.MovieListRoute>()
                val viewModel: MovieListViewModel = koinViewModel {
                    parametersOf(route.title, route.queryParameters)
                }

                MovieListScreen(
                    viewModel = viewModel,
                    type = route.screenType,
                    onBackClick = { navController.popBackStack() },
                    onSettingsClick = {},
                    onMovieClick = { movie ->
                        navController.navigate(MovieGraph.MovieRoute(movie.id)) {
                            launchSingleTop = true
                        }
                    },
                )
            }
        }
    }
}
