package com.mordva.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mordva.navigation.AboutAppGraph
import com.mordva.navigation.SettingsGraph
import com.mordva.ui.theme.ColorGradient1
import com.mordva.ui.theme.ColorGradient2
import com.mordva.ui.theme.ColorGradient3
import com.mordva.ui.theme.ColorGradient4
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.other.animatedBorder
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ProfileScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            ProfileContent(
                name = "Урайкин Роман",
                email = "UraykinRab@yandex.ru"
            )

            Spacer(modifier = Modifier.height(30.dp))
            HorizontalDivider()

            SettingsList(
                onSupport = {},
                onAbout = {
                    navController.navigate(AboutAppGraph) {
                        launchSingleTop = true
                    }
                },
                onSound = {
                    navController.navigate(SettingsGraph.SoundRoute) {
                        launchSingleTop = true
                    }
                },
                onConf = {
                    navController.navigate(SettingsGraph.ConfidentialRoute) {
                        launchSingleTop = true
                    }
                },
                onLanguage = {},
                onData = {},
                onDecor = {
                    navController.navigate(SettingsGraph.DecorRoute) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
private fun ColumnScope.ProfileContent(
    name: String,
    email: String
) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .animatedBorder(
                borderColors = listOf(
                    ColorGradient1,
                    ColorGradient2,
                    ColorGradient3,
                    ColorGradient4
                ),
                backgroundColor = MaterialTheme.colorScheme.background,
                borderWidth = 2.dp
            )
            .padding(20.dp)
    ) {
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(Resources.Icons.Duck),
            contentDescription = null
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = name,
        fontSize = Typography.titleMedium.fontSize,
        fontWeight = Typography.titleMedium.fontWeight,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = email,
        fontSize = Typography.bodyMedium.fontSize,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
}
