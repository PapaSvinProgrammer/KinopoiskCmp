package com.mordva.settings.presentation.widget.content

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.settings.presentation.widget.card.ThemeCard
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ChangeThemeContent(
    themeState: Int,
    onChangeTheme: (Int) -> Unit
) {
    Text(
        text = stringResource(Resources.Strings.Theme),
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    )
    Spacer(modifier = Modifier.height(15.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ThemeCard(
            text = stringResource(Resources.Strings.SystemTheme),
            icon = painterResource(Resources.Icons.Brightness),
            width = 130.dp,
            onClick = { onChangeTheme(1) },
            isFocus = themeState == 1
        )

        ThemeCard(
            text = stringResource(Resources.Strings.Dark),
            icon = painterResource(Resources.Icons.Dark),
            onClick = { onChangeTheme(2) },
            isFocus = themeState == 2
        )

        ThemeCard(
            text = stringResource(Resources.Strings.Light),
            icon = painterResource(Resources.Icons.Light),
            isFocus = themeState == 3,
            onClick = { onChangeTheme(3) }
        )
    }
}
