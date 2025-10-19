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
import com.mordva.movie.utils.toScreenObject
import com.mordva.navigation.CollectionListGraph
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.ImageListGraph
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.MovieListGraph
import com.mordva.navigation.PersonGraph
import com.mordva.util.Constants
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
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
                val viewModel: MovieViewModel = koinViewModel { parametersOf(route.id) }

                MovieScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() },
                    onPersonClick = { person ->
                        navController.navigate(PersonGraph.PersonRoute(person.id)) {
                            launchSingleTop = true
                        }
                    },
                    onCollectionClick = { collection ->
                        val query = arrayListOf(
                            Constants.LISTS_FIELD to collection.slug.toString(),
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC
                        )

                        navController.navigate(
                            MovieListGraph.MovieListRoute(
                                title = collection.name ?: "",
                                queryParameters = query
                            )
                        ) { launchSingleTop = true }
                    },
                    onMovieClick = { movie ->
                        navController.navigate(MovieGraph.MovieRoute(movie.id))
                    },
                    onShowAllPersonsClick = { persons ->
                        navController.navigate(
                            GroupPersonRoute(persons.toScreenObject())
                        ) { launchSingleTop = true }
                    },
                    onWatchabilityClick = { watchability ->
                        navController.navigate(
                            WatchabilityListRoute(
                                watchability = watchability.toScreenObject()
                            )
                        ) {
                            launchSingleTop = true
                        }
                    },
                    onShowAllCollectionsClick = { listId ->
                        navController.navigate(
                            CollectionListGraph.CollectionListRoute(listId = listId)
                        ) {
                            launchSingleTop = true
                        }
                    },
                    onShowAllImagesClick = {
                        navController.navigate(ImageListGraph.ImageListRoute(route.id)) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<WatchabilityListRoute>(
                typeMap = mapOf(
                    typeOf<WatchabilityScreenObject>() to WatchabilityType
                )
            ) {
                val route = it.toRoute<WatchabilityListRoute>()

                WatchabilityListScreen(
                    watchability = route.watchability,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable<GroupPersonRoute>(
                typeMap = mapOf(
                    typeOf<List<PersonMovieScreenObject>>() to PersonMovieListScreenObjectType
                )
            ) {
                val route = it.toRoute<GroupPersonRoute>()
                val viewModel: GroupPersonViewModel = koinViewModel {
                    parametersOf(route.persons)
                }

                GroupPersonsScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() },
                    onPersonClick = { person ->
                        navController.navigate(PersonGraph.PersonRoute(person.id)) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
