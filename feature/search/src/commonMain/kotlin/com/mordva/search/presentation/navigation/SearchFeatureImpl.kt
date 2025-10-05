package com.mordva.search.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.SearchGraph
import com.mordva.search.presentation.searchResult.SearchResultScreen
import com.mordva.search.presentation.searchResult.SearchResultViewModel
import com.mordva.search.presentation.searchScreen.SearchScreen
import com.mordva.search.presentation.searchScreen.SearchViewModel
import com.mordva.search.presentation.searchSettings.SearchSettingsScreen
import com.mordva.search.presentation.searchSettings.SearchSettingsViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

class SearchFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<SearchGraph>(
            startDestination = SearchRoute
        ) {
            composable<SearchRoute> {
                val viewModel: SearchViewModel = koinViewModel()

                SearchScreen(
                    navController = navController,
                    viewModel = viewModel,
                )
            }

            composable<SearchResultRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val data = it.toRoute<SearchResultRoute>()
                val viewModel: SearchResultViewModel = koinViewModel()

                SearchResultScreen(
                    navController = navController,
                    viewModel = viewModel,
                    queryParameters = data.queryParameters,
                )
            }

            composable<SearchSettingsRoute> {
                val viewModel: SearchSettingsViewModel = koinViewModel()

                SearchSettingsScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}