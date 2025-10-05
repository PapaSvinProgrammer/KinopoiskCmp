package com.mordva.kinopoiskkmp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mordva.navigation.FeatureApi
import com.mordva.search.presentation.navigation.SearchFeatureImpl
import com.mordva.ui.theme.MovieAppTheme
import com.mordva.ui.util.InitCoil
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    InitCoil()

    val featureList: List<FeatureApi> = listOf(
//        ProfileFeatureImpl(),
//        HomeFeatureImpl(),
//        AboutAppFeatureImpl(),
//        AwardListFeatureImpl(),
//        CollectionFeatureImpl(),
//        FavoriteFeatureImpl(),
//        MovieFeatureImpl(),
//        MovieListFeatureImpl(),
        SearchFeatureImpl(),
//        OtpFeatureImpl(),
//        PersonFeatureImpl(),
//        PersonPodiumListFeatureImpl(),
//        SettingsFeatureImpl(),
//        AboutAppFeatureImpl()
    )

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination
    val snackBarHostState = remember { SnackbarHostState() }

    MovieAppTheme(darkTheme = true) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            bottomBar = {

            }
        ) { paddingValues ->
            AppNavigation(
                startDestination = true,
                navController = navController,
                list = featureList
            )
        }
    }
}