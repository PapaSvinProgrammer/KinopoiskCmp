package com.mordva.movie.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.domain.model.WatchabilityScreenObject
import com.mordva.movie.presentation.groupPerson.GroupPersonViewModel
import com.mordva.movie.presentation.groupPerson.GroupPersonsScreen
import com.mordva.movie.presentation.movie.MovieScreen
import com.mordva.movie.presentation.movie.MovieViewModel
import com.mordva.movie.presentation.watchability.WatchabilityListScreen
import com.mordva.movie.utils.PersonMovieListScreenObjectType
import com.mordva.movie.utils.WatchabilityType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieGraph
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

class MovieFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<MovieGraph>(
            startDestination = MovieGraph.MovieRoute(5249436)
        ) {
            composable<MovieGraph.MovieRoute> {
                val route = it.toRoute<MovieGraph.MovieRoute>()
                val viewModel: MovieViewModel = koinViewModel()

                MovieScreen(
                    navController = navController,
                    viewModel = viewModel,
                    id = route.id
                )
            }

            composable<WatchabilityListRoute>(
                typeMap = mapOf(
                    typeOf<WatchabilityScreenObject>() to WatchabilityType
                )
            ) {
                val route = it.toRoute<WatchabilityListRoute>()

                WatchabilityListScreen(
                    navController = navController,
                    watchability = route.watchability
                )
            }

            composable<GroupPersonRoute>(
                typeMap = mapOf(
                    typeOf<List<PersonMovieScreenObject>>() to PersonMovieListScreenObjectType
                )
            ) {
                val route = it.toRoute<GroupPersonRoute>()
                val viewModel: GroupPersonViewModel = koinViewModel()

                GroupPersonsScreen(
                    navController = navController,
                    viewModel = viewModel,
                    list = route.persons
                )
            }
        }
    }
}
