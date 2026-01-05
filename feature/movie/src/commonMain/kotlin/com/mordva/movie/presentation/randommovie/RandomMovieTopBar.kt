package com.mordva.movie.presentation.randommovie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Spacer
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun RandomMovieTopBar(
    modifier: Modifier = Modifier,
    onAction: (RandomMovieAction) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .statusBarsPadding()
            .height(Spacer.M64)
            .fillMaxWidth()
            .padding(horizontal = Spacer.M4)
    ) {
        IconButton(onClick = { onAction(RandomMovieAction.GoBack) }) {
            Icon(
                painter = painterResource(Resources.Icons.Close),
                contentDescription = null,
            )
        }

        IconButton(onClick = { onAction(RandomMovieAction.Refresh) }) {
            Icon(
                painter = painterResource(Resources.Icons.Refresh),
                contentDescription = null,
            )
        }
    }
}