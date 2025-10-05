package com.mordva.kinopoiskkmp.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.SearchGraph

@Composable
fun AppNavigation(
    startDestination: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    list: List<FeatureApi>
) {
    NavHost(
        navController = navController,
        startDestination = SearchGraph,
        modifier = modifier
    ) {
        list.forEach {
            it.registerGraph(this, navController)
        }
    }
}
