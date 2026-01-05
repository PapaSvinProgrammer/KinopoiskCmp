package com.mordva.kinopoiskkmp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mordva.awards_list.presentation.navigation.AwardListFeatureImpl
import com.mordva.collection_list.presentation.navigation.CollectionFeatureImpl
import com.mordva.favorite.navigation.FavoriteFeatureImpl
import com.mordva.images_list.presentation.navigation.ImageListFeatureImpl
import com.mordva.movie.navigation.MovieFeatureImpl
import com.mordva.navigation.FeatureApi
import com.mordva.otp.navigatoin.OtpFeatureImpl
import com.mordva.person.navigation.PersonFeatureImpl
import com.mordva.profile.navigation.ProfileFeatureImpl
import com.mordva.search.navigation.SearchFeatureImpl
import com.mordva.settings.navigation.SettingsFeatureImpl
import com.mordva.ui.theme.MovieAppTheme
import com.mordva.ui.util.InitCoil
import com.mordva.ui.widget.navigation.BottomBarItems
import com.mordva.ui.widget.navigation.HazeBottomBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    InitCoil()

    val featureList: List<FeatureApi> = listOf(
        ProfileFeatureImpl(),
        AwardListFeatureImpl(),
        CollectionFeatureImpl(),
        FavoriteFeatureImpl(),
        MovieFeatureImpl(),
        SearchFeatureImpl(),
        OtpFeatureImpl(),
        PersonFeatureImpl(),
        SettingsFeatureImpl(),
        ImageListFeatureImpl(),
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
//                HazeBottomBar(
//                    tabs = BottomBarItems.items,
//                    navController = navController,
//                    visible = true
//                )
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