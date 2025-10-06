package com.mordva.profile.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.ProfileGraph
import com.mordva.profile.ProfileScreen

class ProfileFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<ProfileGraph>(
            startDestination = ProfileRoute
        ) {
            composable<ProfileRoute> {
                ProfileScreen(
                    navController = navController,
                )
            }
        }
    }
}