package com.mordva.settings.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.SettingsGraph
import com.mordva.settings.presentation.confidential.ConfidentialScreen
import com.mordva.settings.presentation.confidential.ConfidentialViewModel
import com.mordva.settings.presentation.decor.DecorScreen
import com.mordva.settings.presentation.decor.DecorViewModel
import com.mordva.settings.presentation.sound.SoundScreen
import com.mordva.settings.presentation.sound.SoundViewModel
import org.koin.compose.viewmodel.koinViewModel

class SettingsFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<SettingsGraph>(
            startDestination = SettingsGraph.DecorRoute
        ) {
            composable<SettingsGraph.DecorRoute> {
                val viewModel: DecorViewModel = koinViewModel()

                DecorScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable<SettingsGraph.SoundRoute> {
                val viewModel: SoundViewModel = koinViewModel()

                SoundScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable<SettingsGraph.ConfidentialRoute> {
                val viewModel: ConfidentialViewModel = koinViewModel()

                ConfidentialScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}