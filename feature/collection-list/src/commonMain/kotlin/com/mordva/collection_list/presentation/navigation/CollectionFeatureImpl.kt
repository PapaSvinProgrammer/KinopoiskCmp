package com.mordva.collection_list.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.collection_list.presentation.CollectionListScreen
import com.mordva.collection_list.presentation.CollectionListViewModel
import com.mordva.navigation.CollectionListGraph
import com.mordva.navigation.FeatureApi
import org.koin.compose.viewmodel.koinViewModel

class CollectionFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<CollectionListGraph>(
            startDestination = CollectionListGraph.CollectionListRoute(null, listOf())
        ) {
            composable<CollectionListGraph.CollectionListRoute> {
                val route = it.toRoute<CollectionListGraph.CollectionListRoute>()

                val viewModel: CollectionListViewModel = koinViewModel()

                if (route.category != null) {
                    CollectionListScreen(
                        navController = navController,
                        viewModel = viewModel,
                        category = route.category ?: "",
                    )
                }
                else if (route.listId.isNotEmpty()) {
                    CollectionListScreen(
                        navController = navController,
                        viewModel = viewModel,
                        listId = route.listId,
                    )
                }
                else {
                    CollectionListScreen(
                        navController = navController,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}