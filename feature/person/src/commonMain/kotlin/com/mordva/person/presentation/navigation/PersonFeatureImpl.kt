package com.mordva.person.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.PersonGraph
import com.mordva.person.presentation.PersonDetailScreen
import com.mordva.person.presentation.PersonScreen
import com.mordva.person.presentation.PersonViewModel
import org.koin.compose.viewmodel.koinViewModel

class PersonFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<PersonGraph>(
            startDestination = PersonGraph.PersonRoute(1)
        ) {
            composable<PersonGraph.PersonRoute> {
                val route = it.toRoute<PersonGraph.PersonRoute>()
                val viewModel: PersonViewModel = koinViewModel()

                PersonScreen(
                    navController = navController,
                    viewModel = viewModel,
                    id = route.id
                )
            }

            composable<PersonDetailRoute> {
                val route = it.toRoute<PersonDetailRoute>()
                val viewModel: PersonViewModel = koinViewModel()

                PersonDetailScreen(
                    navController = navController,
                    viewModel = viewModel,
                    id = route.id
                )
            }
        }
    }
}