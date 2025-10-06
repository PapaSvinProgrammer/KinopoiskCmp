package com.mordva.movie_list.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.base_view_models.MovieListViewModel
import com.mordva.movie_list.MovieListScreen
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieListGraph
import org.koin.compose.viewmodel.koinViewModel
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
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val route = it.toRoute<MovieListGraph.MovieListRoute>()
                val viewModel: MovieListViewModel = koinViewModel()

                MovieListScreen(
                    navController = navController,
                    viewModel = viewModel,
                    title = route.title,
                    queryParameters = route.queryParameters
                )
            }
        }
    }
}