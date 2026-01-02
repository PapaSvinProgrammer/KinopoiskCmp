package com.mordva.otp.navigatoin

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.OtpGraph
import com.mordva.otp.presentation.CreateOtpScreen
import com.mordva.otp.presentation.DefaultOtpScreen
import com.mordva.otp.presentation.DisableOtpScreen
import com.mordva.otp.presentation.OtpViewModel
import org.koin.compose.viewmodel.koinViewModel

class OtpFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<OtpGraph>(
            startDestination = OtpGraph.OtpRoute(isCreate = true, isDisable = true)
        ) {
            composable<OtpGraph.OtpRoute> {
                val route = it.toRoute<OtpGraph.OtpRoute>()

                val viewModel: OtpViewModel = koinViewModel()

                if (route.isDisable) {
                    DisableOtpScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                } else {
                    if (route.isCreate) {
                        CreateOtpScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    } else {
                        DefaultOtpScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}