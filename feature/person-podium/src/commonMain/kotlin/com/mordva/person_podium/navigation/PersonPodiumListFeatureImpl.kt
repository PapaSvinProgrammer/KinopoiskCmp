package com.mordva.person_podium.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.base_view_models.PersonListViewModel
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.PersonPodiumListGraph
import com.mordva.person_podium.PersonPodiumListScreen
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

class PersonPodiumListFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<PersonPodiumListGraph>(
            startDestination = PersonPodiumListGraph.PersonPodiumListRoute("", listOf())
        ) {
            composable<PersonPodiumListGraph.PersonPodiumListRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val route = it.toRoute<PersonPodiumListGraph.PersonPodiumListRoute>()
                val viewModel: PersonListViewModel = koinViewModel()

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