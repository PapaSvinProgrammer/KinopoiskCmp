package com.mordva.home.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.base_view_models.MovieListViewModel
import com.mordva.home.presentation.HomeDetailListScreen
import com.mordva.home.presentation.HomeScreen
import com.mordva.home.presentation.HomeViewModel
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.HomeGraph
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

private typealias HomeDetailParams = List<Pair<String, String>>

class HomeFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<HomeGraph>(
            startDestination = HomeRoute
        ) {
            composable<HomeRoute> {
                val viewModel: HomeViewModel = koinViewModel()

                HomeScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable<HomeDetailListRoute>(
                typeMap = mapOf(
                    typeOf<HomeDetailParams>() to CustomNavType.ListTypePair
                )
            ) {
                val viewModel: MovieListViewModel = koinViewModel()
                val route = it.toRoute<HomeDetailListRoute>()

                HomeDetailListScreen(
                    navController = navController,
                    viewModel = viewModel,
                    title = route.title,
                    queryParameters = route.queryParameters
                )
            }
        }
    }
}