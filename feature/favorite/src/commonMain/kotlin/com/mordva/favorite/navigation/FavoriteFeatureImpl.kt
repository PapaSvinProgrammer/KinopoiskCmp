package com.mordva.favorite.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.favorite.FavoriteScreen
import com.mordva.navigation.FavoriteGraph
import com.mordva.navigation.FeatureApi

class FavoriteFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<FavoriteGraph>(
            startDestination = FavoriteRoute
        ) {
            composable<FavoriteRoute> {
                FavoriteScreen(
                    navController = navController
                )
            }
        }
    }
}