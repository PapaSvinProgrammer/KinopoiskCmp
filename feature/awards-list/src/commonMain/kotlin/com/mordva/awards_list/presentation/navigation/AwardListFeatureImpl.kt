package com.mordva.awards_list.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.awards_list.presentation.AwardListScreen
import com.mordva.awards_list.presentation.AwardListViewModel
import com.mordva.navigation.AwardListGraph
import com.mordva.navigation.FeatureApi
import org.koin.compose.viewmodel.koinViewModel

class AwardListFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<AwardListGraph>(
            startDestination = AwardListGraph.AwardsListRoute(1, false)
        ) {
            composable<AwardListGraph.AwardsListRoute> {
                val route = it.toRoute<AwardListGraph.AwardsListRoute>()
                val viewModel: AwardListViewModel = koinViewModel()

                AwardListScreen(
                    navController = navController,
                    viewModel = viewModel,
                    id = route.id,
                    isMovie = route.isMovie
                )
            }
        }
    }
}