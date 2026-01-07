package com.mordva.settings.presentation.aboutapp

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AboutAppScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(Resources.Strings.AboutApp))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

    }
}