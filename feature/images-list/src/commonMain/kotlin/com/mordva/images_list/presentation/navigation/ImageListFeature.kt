package com.mordva.images_list.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.images_list.presentation.ImageListScreen
import com.mordva.images_list.presentation.ImageListViewModel
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.ImageListGraph
import org.koin.compose.viewmodel.koinViewModel

class ImageListFeatureImpl() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<ImageListGraph>(
            startDestination = ImageListGraph.ImageListRoute(4445150)
        ) {
            composable<ImageListGraph.ImageListRoute> {
                val route = it.toRoute<ImageListGraph.ImageListRoute>()
                val viewModel: ImageListViewModel = koinViewModel()

                ImageListScreen(
                    navController = navController,
                    viewModel = viewModel,
                    movieId = route.movieId
                )
            }
        }
    }
}