package com.mordva.home.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.home.presentation.HomeScreen
import com.mordva.home.presentation.HomeViewModel
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.HomeGraph
import org.koin.compose.viewmodel.koinViewModel

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
        }
    }
}