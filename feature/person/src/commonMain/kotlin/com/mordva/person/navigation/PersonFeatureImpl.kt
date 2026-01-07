package com.mordva.person.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.PersonGraph
import com.mordva.navigation.PersonPodiumListGraph
import com.mordva.person.presentation.person.PersonDetailScreen
import com.mordva.person.presentation.person.PersonScreen
import com.mordva.person.presentation.person.PersonViewModel
import com.mordva.person.presentation.personpodium.PersonPodiumListScreen
import com.mordva.person.presentation.personpodium.PersonPodiumListViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

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

            composable<PersonPodiumListGraph.PersonPodiumListRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val route = it.toRoute<PersonPodiumListGraph.PersonPodiumListRoute>()
                val viewModel: PersonPodiumListViewModel = koinViewModel()

                PersonPodiumListScreen(
                    navController = navController,
                    viewModel = viewModel,
                    title = route.title,
                    queryParameters = route.queryParameters
                )
            }
        }
    }
}